package ynu.mediinsight.MediInsightBackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.mediinsight.MediInsightBackend.entity.po.Registration;
import ynu.mediinsight.MediInsightBackend.mapper.RegistrationMapper;
import ynu.mediinsight.MediInsightBackend.service.RegistrationService;

import java.util.List;

@Service
public class RegistrationServiceImpl extends ServiceImpl<RegistrationMapper, Registration> implements RegistrationService {
    private final RegistrationMapper registrationMapper;

    public RegistrationServiceImpl(RegistrationMapper registrationMapper) {
        this.registrationMapper = registrationMapper;
    }

    @Override
    public List<Registration> getAllWaitingRegistration() {
        return this.registrationMapper.selectAllWaitingRegistration();
    }

    @Override
    public List<Registration> getRegistrationHistoryByPatientId(int id) {
        return this.registrationMapper.selectRegistrationHistoryByPatientId(id);
    }
}
