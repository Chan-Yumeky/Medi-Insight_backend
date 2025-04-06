package ynu.mediinsight.MediInsightBackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynu.mediinsight.MediInsightBackend.dto.response.DoctorVO;
import ynu.mediinsight.MediInsightBackend.dto.response.PatientVO;
import ynu.mediinsight.MediInsightBackend.dto.response.RegistrationVO;
import ynu.mediinsight.MediInsightBackend.entity.po.*;
import ynu.mediinsight.MediInsightBackend.mapper.*;
import ynu.mediinsight.MediInsightBackend.service.RegistrationService;
import ynu.mediinsight.MediInsightBackend.utils.Proxy;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationServiceImpl extends ServiceImpl<RegistrationMapper, Registration> implements RegistrationService {
    private final RegistrationMapper registrationMapper;
    private final DrugMapper drugMapper;
    private final DiagnosisMapper diagnosisMapper;
    private final ProcedureMapper procedureMapper;
    private final AccountMapper accountMapper;
    private final ExpertScheduleMapper expertScheduleMapper;

    public RegistrationServiceImpl(RegistrationMapper registrationMapper, DrugMapper drugMapper, DiagnosisMapper diagnosisMapper, ProcedureMapper procedureMapper, AccountMapper accountMapper, ExpertScheduleMapper expertScheduleMapper) {
        this.registrationMapper = registrationMapper;
        this.drugMapper = drugMapper;
        this.diagnosisMapper = diagnosisMapper;
        this.procedureMapper = procedureMapper;
        this.accountMapper = accountMapper;
        this.expertScheduleMapper = expertScheduleMapper;
    }

    @Override
    public List<RegistrationVO> getAllWaitingRegistration() {
        List<Registration> getAllWaitingRegistration = this.registrationMapper.selectAllWaitingRegistration();
        List<RegistrationVO> registrationVOList = new ArrayList<>();
        for (Registration registration : getAllWaitingRegistration) {
            Account account = this.accountMapper.selectById(registration.getPid());
            RegistrationVO registrationVO = Proxy.registration2RegistrationVO(registration);
            PatientVO patientVO = Proxy.account2Patient(account);
            registrationVO.setPatient(patientVO);
            registrationVOList.add(registrationVO);
        }
        return registrationVOList;
    }

    @Override
    public List<RegistrationVO> getRegistrationHistoryByPatientId(int id) {
        List<Registration> registrations = this.registrationMapper.selectRegistrationHistoryByPatientId(id);
        List<RegistrationVO> registrationVOS = new ArrayList<RegistrationVO>();
        for(Registration registration : registrations) {
            RegistrationVO registrationVO = Proxy.registration2RegistrationVO(registration);
            Account account = this.accountMapper.selectById(registration.getDid());
            DoctorVO doctorVO = Proxy.account2Doctor(account);
            Account patientAccount = this.accountMapper.selectById(registration.getPid());
            PatientVO patientVO = Proxy.account2Patient(patientAccount);
            registrationVO.setPatient(patientVO);
            registrationVO.setDoctor(doctorVO);
            List<Diagnosis> diagnosis = this.diagnosisMapper.selectDiagnosisByVid(registration.getId());
            List<Procedure> procedures = this.procedureMapper.selectProcedureByVid(registration.getId());
            List<Drug> drugs = this.drugMapper.selectAllDrugsByVid(registration.getId());
            registrationVO.setDiagnosis(diagnosis);
            registrationVO.setProcedures(procedures);
            registrationVO.setDrugs(drugs);
            registrationVOS.add(registrationVO);
        }
        return registrationVOS;
    }

    @Override
    @Transactional
    public void addRegistration(Registration registration) {
        // 获取医生信息
        Account doctor = accountMapper.selectById(registration.getDid());
        if (doctor == null) {
            throw new RuntimeException("医生不存在");
        }

        // 添加专家预约验证逻辑
        if (doctor.getIsExpert() == 1) {
            validateExpertRegistration(registration);
        }
        
        // 继续原有的预约逻辑
        this.registrationMapper.insert(registration);
    }

    // 新增专家预约验证方法
    private void validateExpertRegistration(Registration registration) {
        // 检查是否在专家排班时间内
        ExpertSchedule schedule = expertScheduleMapper.getAvailableSchedule(
            registration.getDid(),
            registration.getStartTime(),
            registration.getEndTime()
        );
        
        if (schedule == null) {
            throw new RuntimeException("该时段不在专家出诊时间内");
        }
        
        // 检查预约人数是否已满
        if (schedule.getCurrentPatients() >= schedule.getMaxPatients()) {
            throw new RuntimeException("该时段预约已满");
        }
        
        // 更新预约人数
        int updated = expertScheduleMapper.incrementCurrentPatients(schedule.getId());
        if (updated == 0) {
            throw new RuntimeException("预约失败，请重试");
        }
    }
}
