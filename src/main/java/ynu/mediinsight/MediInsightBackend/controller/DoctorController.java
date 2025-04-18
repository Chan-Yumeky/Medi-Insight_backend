package ynu.mediinsight.MediInsightBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.mediinsight.MediInsightBackend.dto.response.DoctorVO;
import ynu.mediinsight.MediInsightBackend.entity.po.Account;
import ynu.mediinsight.MediInsightBackend.service.AccountService;
import ynu.mediinsight.MediInsightBackend.utils.Proxy;

import java.util.List;

@RestController
@RequestMapping("/auth/api")
public class DoctorController {
    private final AccountService accountService;

    public DoctorController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 根据id获取患者信息
     *
     * @param id 患者id
     * @return 患者信息
     */
    @Operation(summary = "根据id获取医生信息", description = "根据id获取医生信息")
    @GetMapping("/doctors/{id}")
    public DoctorVO getPatientById(@PathVariable int id) {
        Account account = this.accountService.getById(id);
        DoctorVO doctorVO = Proxy.account2Doctor(account);
        return doctorVO;
    }

    /**
     * 获取所有医生信息
     * @return 医生信息列表
     */
    @Operation(summary = "小程序端获取所有医生信息", description = "小程序端获取所有医生信息")
    @GetMapping("get-doctors")
    public List<DoctorVO> getAllDoctors() {
        return accountService.getAllDoctors();
    }

    /**
     * 获取所有专家医生信息
     * @return 专家医生信息列表
     */
    @Operation(summary = "获取所有专家医生列表", description = "用于管理员排班和专家医生查看排班")
    @GetMapping("/doctors/experts")
    public List<DoctorVO> getExpertDoctors() {
        return accountService.getExpertDoctors();
    }
}
