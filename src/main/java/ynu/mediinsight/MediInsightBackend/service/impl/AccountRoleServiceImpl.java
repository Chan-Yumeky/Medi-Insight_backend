package ynu.mediinsight.MediInsightBackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.mediinsight.MediInsightBackend.entity.po.AccountRole;
import ynu.mediinsight.MediInsightBackend.mapper.AccountRoleMapper;
import ynu.mediinsight.MediInsightBackend.service.AccountRoleService;

@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRole>
        implements AccountRoleService {
    @Override
    public AccountRole findRIDByUID(int uid) {
        return this.query()
                .eq("uid", uid)
                .one();
    }
}
