package ynu.mediinsight.MediInsightBackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ynu.mediinsight.MediInsightBackend.entity.po.ExpertSchedule;
import java.util.Date;
import java.util.List;

@Mapper
public interface ExpertScheduleMapper extends BaseMapper<ExpertSchedule> {
    @Select("SELECT * FROM expert_schedule " +
            "WHERE doctor_id = #{doctorId} " +
            "AND DATE(schedule_date) = DATE(#{startTime}) " +
            "AND #{startTime} >= start_time " +
            "AND #{endTime} <= end_time " +
            "AND status = 0 " +
            "LIMIT 1")
    ExpertSchedule getAvailableSchedule(Integer doctorId, Date startTime, Date endTime);

    @Select("SELECT * FROM expert_schedule " +
            "WHERE doctor_id = #{doctorId} " +
            "AND DATE(schedule_date) BETWEEN DATE(#{startDate}) AND DATE(#{endDate}) " +
            "AND status != 2 " +
            "ORDER BY schedule_date, start_time")
    List<ExpertSchedule> selectByDateRange(Integer doctorId, Date startDate, Date endDate);

    @Select("SELECT COUNT(*) FROM expert_schedule " +
            "WHERE doctor_id = #{doctorId} " +
            "AND DATE(schedule_date) = DATE(#{scheduleDate}) " +
            "AND status != 2 " +
            "AND ((start_time <= #{startTime} AND end_time > #{startTime}) " +
            "OR (start_time < #{endTime} AND end_time >= #{endTime}) " +
            "OR (start_time >= #{startTime} AND end_time <= #{endTime}))")
    int countConflictSchedules(Integer doctorId, Date scheduleDate, Date startTime, Date endTime);

    @Update("UPDATE expert_schedule SET current_patients = current_patients + 1, " +
            "status = CASE WHEN current_patients + 1 >= max_patients THEN 1 ELSE 0 END " +
            "WHERE id = #{id} AND status = 0")
    int incrementCurrentPatients(Integer id);
} 