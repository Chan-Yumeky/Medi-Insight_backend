package ynu.mediinsight.MediInsightBackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.mediinsight.MediInsightBackend.dto.response.RegistrationVO;
import ynu.mediinsight.MediInsightBackend.entity.po.Registration;

import java.util.List;

public interface RegistrationService extends IService<Registration> {
    List<RegistrationVO> getAllWaitingRegistration();

    List<RegistrationVO> getRegistrationHistoryByPatientId(int id);

    String addRegistration(Registration registration);

    String finishRecord(Registration registration);
}
