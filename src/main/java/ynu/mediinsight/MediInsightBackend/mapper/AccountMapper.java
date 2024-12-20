package ynu.mediinsight.MediInsightBackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.mediinsight.MediInsightBackend.entity.po.Account;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    @Select("select * from account where id=#{id}")
    public Account getAccountById(int id);
}
