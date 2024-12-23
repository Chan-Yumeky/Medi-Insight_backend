package ynu.mediinsight.MediInsightBackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.mediinsight.MediInsightBackend.entity.po.AccountRole;
import ynu.mediinsight.MediInsightBackend.mapper.AccountRoleMapper;
import ynu.mediinsight.MediInsightBackend.service.AccountRoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRole>
        implements AccountRoleService {

    @Override
    public AccountRole findRIDByUID(int uid) {
        return this.query()
                .eq("uid", uid)
                .one();
    }

    @Override
    public boolean registerAccountRole(int uid) {
        AccountRole accountRole = new AccountRole(null, uid, 3);
        return this.save(accountRole);
    }

    @Override
    public List<Integer> getAllDoctorsID() {
        QueryWrapper<AccountRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid", 2);

        List<AccountRole> accountRoles = this.list(queryWrapper);

        return accountRoles.stream()
                .map(AccountRole::getUid)
                .collect(Collectors.toList());
    }

}
