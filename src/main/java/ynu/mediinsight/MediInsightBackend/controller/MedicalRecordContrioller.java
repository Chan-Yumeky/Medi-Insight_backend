package ynu.mediinsight.MediInsightBackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.mediinsight.MediInsightBackend.dto.request.MedicalRecordRO;
import ynu.mediinsight.MediInsightBackend.service.MedicalRecordService;

@RestController
@RequestMapping("/auth/api")
public class MedicalRecordContrioller {
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordContrioller(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping("/medical/save")
    public MedicalRecordRO save(@RequestBody MedicalRecordRO medicalRecordRO) {
        this.medicalRecordService.save(medicalRecordRO);
        return medicalRecordRO;
    }
}
