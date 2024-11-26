package ynu.mediinsight.MediInsightBackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.mediinsight.MediInsightBackend.entity.po.Registration;

import java.util.List;

@Mapper
public interface RegistrationMapper extends BaseMapper<Registration> {
    @Select("select r.id,a.id as pid,a.username as name,a.gender,a.birthday,a.age,r.status,r.diagnostic,r.source,r.book_time,r.start_time,r.end_time from account as a,registration as r where r.pid=a.id and status=0")
    List<Registration> selectAllWaitingRegistration();
}
