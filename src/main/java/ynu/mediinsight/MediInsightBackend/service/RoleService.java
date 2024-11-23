package ynu.mediinsight.MediInsightBackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.mediinsight.MediInsightBackend.entity.po.Role;

public interface RoleService extends IService<Role> {
    Role findRoleByRID(int rid);
}
