package ynu.mediinsight.MediInsightBackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ynu.mediinsight.MediInsightBackend.entity.po.Registration;
import ynu.mediinsight.MediInsightBackend.service.RegistrationService;

import java.util.List;

@Tag(name = "registration", description = "")
@RestController
@RequestMapping("/auth/api")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registrations/waiting")
    public List<Registration> getWaitingRegistrations() {
        return this.registrationService.getAllWaitingRegistration();
    }
}
