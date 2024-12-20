package ynu.mediinsight.MediInsightBackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.mediinsight.MediInsightBackend.entity.po.Registration;

import java.util.List;

@Mapper
public interface RegistrationMapper extends BaseMapper<Registration> {
    @Select("select * from registration where status=0")
    List<Registration> selectAllWaitingRegistration();

    @Select("select * from registration as r where r.pid=#{id} and status=1")
    List<Registration> selectRegistrationHistoryByPatientId(int id);
}
