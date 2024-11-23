package ynu.mediinsight.MediInsightBackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ynu.mediinsight.MediInsightBackend.entity.po.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
