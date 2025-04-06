package ynu.mediinsight.MediInsightBackend.service;

import ynu.mediinsight.MediInsightBackend.dto.request.ExpertScheduleRO;
import ynu.mediinsight.MediInsightBackend.dto.response.ExpertScheduleVO;
import ynu.mediinsight.MediInsightBackend.entity.po.ExpertSchedule;
import java.util.List;
import java.util.Date;

public interface ExpertScheduleService {
    ExpertScheduleVO addSchedule(ExpertScheduleRO scheduleRO);
    List<ExpertScheduleVO> batchAddSchedule(List<ExpertScheduleRO> scheduleROs);
    List<ExpertScheduleVO> getSchedulesByDateRange(Integer doctorId, Date startDate, Date endDate);
    ExpertScheduleVO updateSchedule(Integer id, ExpertScheduleRO scheduleRO);
    void cancelSchedule(Integer scheduleId);
    // 修改方法签名
    ExpertSchedule getAvailableSchedule(Integer doctorId, Date startTime, Date endTime);
} 