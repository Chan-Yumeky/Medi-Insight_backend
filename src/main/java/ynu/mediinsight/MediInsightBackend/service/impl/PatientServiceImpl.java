package ynu.mediinsight.MediInsightBackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.mediinsight.MediInsightBackend.entity.po.Account;
import ynu.mediinsight.MediInsightBackend.mapper.PatientMapper;
import ynu.mediinsight.MediInsightBackend.service.PatientService;

@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Account> implements PatientService {
    private final PatientMapper patientMapper;
    public PatientServiceImpl(PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }
}
