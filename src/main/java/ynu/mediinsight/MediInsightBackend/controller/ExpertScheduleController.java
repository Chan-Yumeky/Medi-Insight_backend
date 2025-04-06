package ynu.mediinsight.MediInsightBackend.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ynu.mediinsight.MediInsightBackend.dto.request.ExpertScheduleRO;
import ynu.mediinsight.MediInsightBackend.dto.response.ExpertScheduleVO;
import ynu.mediinsight.MediInsightBackend.service.ExpertScheduleService;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("auth/api/expert-schedule")
public class ExpertScheduleController {
    private final ExpertScheduleService expertScheduleService;

    public ExpertScheduleController(ExpertScheduleService expertScheduleService) {
        this.expertScheduleService = expertScheduleService;
    }

    /**
     * 添加单个专家排班
     * @param scheduleRO 排班请求信息
     * @return 创建的排班信息
     */
    @PostMapping("/add")
    public ExpertScheduleVO addSchedule(@RequestBody ExpertScheduleRO scheduleRO) {
        return expertScheduleService.addSchedule(scheduleRO);
    }

    /**
     * 批量添加专家排班
     * @param scheduleROs 排班请求信息列表
     * @return 创建的所有排班信息
     */
    @PostMapping("/batch-add")
    public List<ExpertScheduleVO> batchAddSchedule(@RequestBody List<ExpertScheduleRO> scheduleROs) {
        return expertScheduleService.batchAddSchedule(scheduleROs);
    }

    /**
     * 获取指定医生在日期范围内的排班信息
     * @param doctorId 医生ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 排班信息列表
     */
    @GetMapping("/doctor/{doctorId}")
    public List<ExpertScheduleVO> getSchedulesByDoctor(
            @PathVariable Integer doctorId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        return expertScheduleService.getSchedulesByDateRange(doctorId, startDate, endDate);
    }

    /**
     * 更新排班信息
     * @param id 排班ID
     * @param scheduleRO 更新的排班信息
     * @return 更新后的排班信息
     */
    @PutMapping("/{id}")
    public ExpertScheduleVO updateSchedule(
            @PathVariable Integer id,
            @RequestBody ExpertScheduleRO scheduleRO) {
        return expertScheduleService.updateSchedule(id, scheduleRO);
    }

    /**
     * 取消排班
     * @param id 排班ID
     */
    @DeleteMapping("/{id}")
    public void cancelSchedule(@PathVariable Integer id) {
        expertScheduleService.cancelSchedule(id);
    }
} 