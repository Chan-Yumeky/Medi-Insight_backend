package ynu.mediinsight.MediInsightBackend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.mediinsight.MediInsightBackend.dto.response.PatientVO;
import ynu.mediinsight.MediInsightBackend.entity.po.Account;
import ynu.mediinsight.MediInsightBackend.service.PatientService;

@RestController
@RequestMapping("/auth/api")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping("/patients/{id}")
    public PatientVO getPatientById(@PathVariable int id) {
        PatientVO patientVO = new PatientVO();
        Account patient = this.patientService.getById(id);
        patientVO.setId(id);
        patientVO.setUsername(patient.getUsername());
        patientVO.setGender(patient.getGender());
        patientVO.setAge(patient.getAge());
        patientVO.setPhone(patient.getPhone());
        patientVO.setEmail(patient.getEmail());
        patientVO.setAddress(patient.getAddress());
        patientVO.setBirthday(patient.getBirthday());
        return patientVO;
    }
}
