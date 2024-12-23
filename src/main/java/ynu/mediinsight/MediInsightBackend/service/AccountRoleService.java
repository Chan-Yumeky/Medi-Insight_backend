package ynu.mediinsight.MediInsightBackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.mediinsight.MediInsightBackend.entity.po.AccountRole;

import java.util.List;

public interface AccountRoleService extends IService<AccountRole> {
    AccountRole findRIDByUID(int uid);

    boolean registerAccountRole(int uid);

    List<Integer> getAllDoctorsID();
}
