package ynu.mediinsight.MediInsightBackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import ynu.mediinsight.MediInsightBackend.dto.request.ConfirmResetRO;
import ynu.mediinsight.MediInsightBackend.dto.request.EmailRegisterRO;
import ynu.mediinsight.MediInsightBackend.dto.request.EmailResetRO;
import ynu.mediinsight.MediInsightBackend.dto.response.DoctorVO;
import ynu.mediinsight.MediInsightBackend.entity.po.Account;

import java.util.List;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByUsernameOrEmail(String text);

    String registerEmailVerifyCode(String type, String email, String ip);

    String registerEmailAccount(EmailRegisterRO info);

    String resetConfirm(ConfirmResetRO ro);

    String resetEmailAccountPassword(EmailResetRO ro);

    List<DoctorVO> getAllDoctors();

    /**
     * 获取所有专家医生列表
     * @return 专家医生列表
     */
    List<DoctorVO> getExpertDoctors();
}
