package ynu.mediinsight.MediInsightBackend.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
@TableName("expert_schedule")
public class ExpertSchedule {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer doctorId;      // 专家医生ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date scheduleDate;     // 排班日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;        // 开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;          // 结束时间
    private Integer maxPatients;   // 最大预约人数
    private Integer currentPatients; // 当前预约人数
    private Integer status;        // 状态：0-可预约，1-已满，2-已取消
} 