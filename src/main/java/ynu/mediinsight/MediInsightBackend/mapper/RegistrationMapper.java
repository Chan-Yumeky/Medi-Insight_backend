package ynu.mediinsight.MediInsightBackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ynu.mediinsight.MediInsightBackend.entity.po.Registration;

import java.util.Date;
import java.util.List;

@Mapper
public interface RegistrationMapper extends BaseMapper<Registration> {
    @Select("select * from registration where status=0")
    List<Registration> selectAllWaitingRegistration();

    @Select("select * from registration as r where r.pid=#{id} and status=1")
    List<Registration> selectRegistrationHistoryByPatientId(int id);

    @Update("update registration set status=1,did=#{did},start_time=#{formattedDate},end_time=#{formattedDate} where id=#{id}")
    void updateRegistrationById(Integer id, Integer did, String formattedDate);

    @Select("SELECT COUNT(*) FROM registration " +
            "WHERE did = #{doctorId} " +
            "AND DATE(book_time) = DATE(#{bookTime}) " +
            "AND status != 2")
    int countRegistrationsByTime(Integer doctorId, Date bookTime);
}
