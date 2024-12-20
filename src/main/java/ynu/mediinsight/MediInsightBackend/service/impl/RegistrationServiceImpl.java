package ynu.mediinsight.MediInsightBackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
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
    public RegistrationServiceImpl(RegistrationMapper registrationMapper, DrugMapper drugMapper, DiagnosisMapper diagnosisMapper, ProcedureMapper procedureMapper, AccountMapper accountMapper) {
        this.registrationMapper = registrationMapper;
        this.drugMapper = drugMapper;
        this.diagnosisMapper = diagnosisMapper;
        this.procedureMapper = procedureMapper;
        this.accountMapper = accountMapper;
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
    public String addRegistration(Registration registration) {

        return "";
    }

    @Override
    public String finishRecord(Registration registration) {
        return "";
    }
}
