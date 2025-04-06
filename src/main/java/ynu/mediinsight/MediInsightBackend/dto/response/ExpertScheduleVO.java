package ynu.mediinsight.MediInsightBackend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class ExpertScheduleVO {
    private Integer id;
    private Integer doctorId;
    private String doctorName;     // 医生姓名
    private String department;     // 科室
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date scheduleDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    private Integer maxPatients;
    private Integer currentPatients;
    private Integer status;        // 0-可预约，1-已满，2-已取消
} 