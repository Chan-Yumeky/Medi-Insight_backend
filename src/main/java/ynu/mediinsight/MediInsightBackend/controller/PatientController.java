package ynu.mediinsight.MediInsightBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.mediinsight.MediInsightBackend.dto.response.PatientVO;
import ynu.mediinsight.MediInsightBackend.entity.po.Account;
import ynu.mediinsight.MediInsightBackend.service.AccountService;
import ynu.mediinsight.MediInsightBackend.utils.Proxy;

@RestController
@RequestMapping("/auth/api")
@Tag(name = "患者相关接口", description = "患者相关接口")
public class PatientController {
    private final AccountService accountService;
    public PatientController( AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 根据id获取患者信息
     *
     * @param id 患者id
     * @return 患者信息
     */
    @Operation(summary = "根据id获取患者信息", description = "根据id获取患者信息")
    @GetMapping("/patients/{id}")
    public PatientVO getPatientById(@PathVariable int id) {
        Account account = this.accountService.getById(id);
        PatientVO patientVO = Proxy.account2Patient(account);
        return patientVO;
    }
}
