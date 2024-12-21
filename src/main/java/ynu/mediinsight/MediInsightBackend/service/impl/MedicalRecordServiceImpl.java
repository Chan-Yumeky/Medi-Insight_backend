package ynu.mediinsight.MediInsightBackend.service.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.servlet.Registration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynu.mediinsight.MediInsightBackend.dto.request.DiagnosisRO;
import ynu.mediinsight.MediInsightBackend.dto.request.DrugRO;
import ynu.mediinsight.MediInsightBackend.dto.request.MedicalRecordRO;
import ynu.mediinsight.MediInsightBackend.dto.request.ProcedureRO;
import ynu.mediinsight.MediInsightBackend.entity.po.Diagnosis;
import ynu.mediinsight.MediInsightBackend.entity.po.Drug;
import ynu.mediinsight.MediInsightBackend.entity.po.Procedure;
import ynu.mediinsight.MediInsightBackend.mapper.DiagnosisMapper;
import ynu.mediinsight.MediInsightBackend.mapper.DrugMapper;
import ynu.mediinsight.MediInsightBackend.mapper.ProcedureMapper;
import ynu.mediinsight.MediInsightBackend.mapper.RegistrationMapper;
import ynu.mediinsight.MediInsightBackend.service.MedicalRecordService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final DrugMapper drugMapper;
    private final ProcedureMapper procedureMapper;
    private final DiagnosisMapper diagnosisMapper;
    private final RegistrationMapper registrationMapper;
    public MedicalRecordServiceImpl(DrugMapper drugMapper, ProcedureMapper procedureMapper, DiagnosisMapper diagnosisMapper, RegistrationMapper registrationMapper) {
        this.drugMapper = drugMapper;
        this.procedureMapper = procedureMapper;
        this.diagnosisMapper = diagnosisMapper;
        this.registrationMapper = registrationMapper;
    }

    @Transactional
    @Override
    public void save(MedicalRecordRO medicalRecordRO) {
        List<Drug> drugList = new ArrayList<>();
        List<DrugRO> drugROList = medicalRecordRO.getDrug();
        if(!drugROList.isEmpty()){
            for(DrugRO drugRO:drugROList){
                Drug drug = new Drug();
                drug.setAtc(drugRO.getNdcCode());
                drug.setName(drugRO.getDrug());
                drug.setVid(medicalRecordRO.getId());
                drugList.add(drug);
            }
            this.drugMapper.insert(drugList);
        }

        List<Procedure> procedureList = new ArrayList<>();
        List<ProcedureRO> procedureROList = medicalRecordRO.getProcedure();
        if(!procedureROList.isEmpty()){
            for(ProcedureRO procedureRO:procedureROList){
                Procedure procedure = new Procedure();
                procedure.setCode(procedureRO.getProcedureCode());
                procedure.setName(procedureRO.getProcedure());
                procedure.setVid(medicalRecordRO.getId());
                procedureList.add(procedure);
            }
            for(Procedure procedure:procedureList){
                this.procedureMapper.insertProcedure(procedure);
            }
        }

        List<Diagnosis> diagnosisList = new ArrayList<>();
        List<DiagnosisRO> diagnosisROList = medicalRecordRO.getDiagnosis();
        if(!diagnosisROList.isEmpty()){
            for(DiagnosisRO diagnosisRO:diagnosisROList){
                Diagnosis diagnosis = new Diagnosis();
                String[] content = diagnosisRO.getDiagnosisContent().split("-");
                List<String> contentList = new ArrayList<>();
                for(String con:content){
                    con = con.trim();
                    contentList.add(con);
                }
                diagnosis.setCode(contentList.get(0));
                diagnosis.setName(contentList.get(1));
                diagnosis.setVid(medicalRecordRO.getId());
                diagnosisList.add(diagnosis);
            }
            this.diagnosisMapper.insert(diagnosisList);

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(date);
            this.registrationMapper.updateRegistrationById(medicalRecordRO.getId(),medicalRecordRO.getDid(),formattedDate);
        }
    }
}
