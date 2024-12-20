package ynu.mediinsight.MediInsightBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ynu.mediinsight.MediInsightBackend.dto.response.RegistrationVO;
import ynu.mediinsight.MediInsightBackend.entity.po.Registration;
import ynu.mediinsight.MediInsightBackend.service.RegistrationService;

import java.util.List;

@Tag(name = "挂号相关接口", description = "用于挂号相关接口")
@RestController
@RequestMapping("/auth/api")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * 获取所有等待挂号的患者
     *
     * @return 等待挂号的患者列表
     */
    @Operation(summary = "获取所有等待挂号的患者", description = "获取所有等待挂号的患者")
    @GetMapping("/registrations/waiting")
    public List<RegistrationVO> getWaitingRegistrations() {
        return this.registrationService.getAllWaitingRegistration();
    }

    /**
     * 根据id获取患者的挂号历史
     *
     * @param id 患者id
     * @return 患者的挂号历史列表
     */
    @Operation(summary = "根据id获取患者的挂号历史", description = "根据id获取患者的挂号历史")
    @GetMapping("/registrations/history/{id}")
    public List<RegistrationVO> getRegistrationById(@PathVariable int id) {
        return this.registrationService.getRegistrationHistoryByPatientId(id);
    }

    @PostMapping("/registrations")
    public String addRegistration(@RequestBody Registration registration) {
        return this.registrationService.addRegistration(registration);
    }

    @PostMapping("/registrations/record")
    public String finishRecord(@RequestBody Registration registration) {
        return this.registrationService.finishRecord(registration);
    }
}
