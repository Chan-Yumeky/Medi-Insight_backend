package ynu.mediinsight.MediInsightBackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import ynu.mediinsight.MediInsightBackend.entity.po.Account;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByUsernameOrEmail(String text);
}
