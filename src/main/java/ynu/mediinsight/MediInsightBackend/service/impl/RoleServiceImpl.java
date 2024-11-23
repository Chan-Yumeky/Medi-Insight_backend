package ynu.mediinsight.MediInsightBackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import ynu.mediinsight.MediInsightBackend.entity.po.Role;
import ynu.mediinsight.MediInsightBackend.mapper.RoleMapper;
import ynu.mediinsight.MediInsightBackend.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public Role findRoleByRID(int rid) {
        return this.query()
                .eq("id", rid)
                .one();
    }
}
