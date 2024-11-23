package ynu.mediinsight.MediInsightBackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.mediinsight.MediInsightBackend.entity.po.AccountRole;

public interface AccountRoleService extends IService<AccountRole> {
    AccountRole findRIDByUID(int uid);
}
