package ynu.mediinsight.MediInsightBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.mediinsight.MediInsightBackend.dto.response.PatientVO;
import ynu.mediinsight.MediInsightBackend.entity.po.Account;
import ynu.mediinsight.MediInsightBackend.service.PatientService;

@RestController
@RequestMapping("/auth/api")
@Tag(name = "患者相关接口", description = "患者相关接口")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * 根据id获取患者信息
     *
     * @param id 患者id
     * @return 患者信息
     */
    @Operation(summary = "根据id获取患者信息", description = "根据id获取患者信息")
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
