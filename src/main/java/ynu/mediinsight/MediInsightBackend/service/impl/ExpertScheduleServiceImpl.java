package ynu.mediinsight.MediInsightBackend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ynu.mediinsight.MediInsightBackend.dto.request.ExpertScheduleRO;
import ynu.mediinsight.MediInsightBackend.dto.response.ExpertScheduleVO;
import ynu.mediinsight.MediInsightBackend.entity.po.ExpertSchedule;
import ynu.mediinsight.MediInsightBackend.entity.po.Account;
import ynu.mediinsight.MediInsightBackend.mapper.ExpertScheduleMapper;
import ynu.mediinsight.MediInsightBackend.mapper.AccountMapper;
import ynu.mediinsight.MediInsightBackend.service.ExpertScheduleService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpertScheduleServiceImpl implements ExpertScheduleService {
    private final ExpertScheduleMapper expertScheduleMapper;
    private final AccountMapper accountMapper;

    public ExpertScheduleServiceImpl(ExpertScheduleMapper expertScheduleMapper, 
                                   AccountMapper accountMapper) {
        this.expertScheduleMapper = expertScheduleMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional
    public ExpertScheduleVO addSchedule(ExpertScheduleRO scheduleRO) {
        Account doctor = accountMapper.selectById(scheduleRO.getDoctorId());
        if (doctor == null || doctor.getIsExpert() != 1) {
            throw new RuntimeException("只能为专家医生设置出诊时间");
        }

        ExpertSchedule schedule = convertToEntity(scheduleRO);
        validateScheduleTime(schedule);
        
        if (checkTimeConflict(schedule)) {
            throw new RuntimeException("该时间段已有排班");
        }

        schedule.setCurrentPatients(0);
        schedule.setStatus(0);
        expertScheduleMapper.insert(schedule);

        return convertToVO(schedule, doctor);
    }

    @Override
    @Transactional
    public List<ExpertScheduleVO> batchAddSchedule(List<ExpertScheduleRO> scheduleROs) {
        List<ExpertScheduleVO> results = new ArrayList<>();
        for (ExpertScheduleRO scheduleRO : scheduleROs) {
            results.add(addSchedule(scheduleRO));
        }
        return results;
    }

    @Override
    public List<ExpertScheduleVO> getSchedulesByDateRange(Integer doctorId, Date startDate, Date endDate) {
        List<ExpertSchedule> schedules = expertScheduleMapper.selectByDateRange(doctorId, startDate, endDate);
        Account doctor = accountMapper.selectById(doctorId);
        return schedules.stream()
                .map(schedule -> convertToVO(schedule, doctor))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ExpertScheduleVO updateSchedule(Integer id, ExpertScheduleRO scheduleRO) {
        ExpertSchedule existing = expertScheduleMapper.selectById(id);
        if (existing == null) {
            throw new RuntimeException("排班记录不存在");
        }
        
        if (existing.getCurrentPatients() > 0) {
            throw new RuntimeException("该时段已有患者预约，无法修改");
        }

        ExpertSchedule schedule = convertToEntity(scheduleRO);
        schedule.setId(id);
        validateScheduleTime(schedule);
        expertScheduleMapper.updateById(schedule);

        Account doctor = accountMapper.selectById(schedule.getDoctorId());
        return convertToVO(schedule, doctor);
    }

    @Override
    @Transactional
    public void cancelSchedule(Integer scheduleId) {
        ExpertSchedule schedule = expertScheduleMapper.selectById(scheduleId);
        if (schedule == null) {
            throw new RuntimeException("排班记录不存在");
        }

        if (schedule.getCurrentPatients() > 0) {
            throw new RuntimeException("该时段已有患者预约，无法取消");
        }

        schedule.setStatus(2);
        expertScheduleMapper.updateById(schedule);
    }

    @Override
    public ExpertSchedule getAvailableSchedule(Integer doctorId, Date startTime, Date endTime) {
        return expertScheduleMapper.getAvailableSchedule(doctorId, startTime, endTime);
    }

    private void validateScheduleTime(ExpertSchedule schedule) {
        if (schedule.getStartTime().after(schedule.getEndTime())) {
            throw new RuntimeException("开始时间不能晚于结束时间");
        }
        
        if (schedule.getScheduleDate().before(new Date())) {
            throw new RuntimeException("不能设置过去的时间");
        }
    }

    private boolean checkTimeConflict(ExpertSchedule schedule) {
        return expertScheduleMapper.countConflictSchedules(
            schedule.getDoctorId(),
            schedule.getScheduleDate(),
            schedule.getStartTime(),
            schedule.getEndTime()
        ) > 0;
    }

    private ExpertSchedule convertToEntity(ExpertScheduleRO ro) {
        ExpertSchedule schedule = new ExpertSchedule();
        schedule.setDoctorId(ro.getDoctorId());
        schedule.setScheduleDate(ro.getScheduleDate());
        schedule.setStartTime(ro.getStartTime());
        schedule.setEndTime(ro.getEndTime());
        schedule.setMaxPatients(ro.getMaxPatients());
        return schedule;
    }

    private ExpertScheduleVO convertToVO(ExpertSchedule schedule, Account doctor) {
        ExpertScheduleVO vo = new ExpertScheduleVO();
        vo.setId(schedule.getId());
        vo.setDoctorId(schedule.getDoctorId());
        vo.setDoctorName(doctor.getUsername());
        vo.setDepartment(doctor.getDepartment());
        vo.setScheduleDate(schedule.getScheduleDate());
        vo.setStartTime(schedule.getStartTime());
        vo.setEndTime(schedule.getEndTime());
        vo.setMaxPatients(schedule.getMaxPatients());
        vo.setCurrentPatients(schedule.getCurrentPatients());
        vo.setStatus(schedule.getStatus());
        return vo;
    }
} 