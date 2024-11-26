package ynu.mediinsight.MediInsightBackend.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Registration {
    @TableId(type = IdType.AUTO)
    private String id;
    private String pid;
    private String name;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;
    private int age;
    private int status;
    private String diagnostic;
    private String did;
    private String doctorName;
    private String department;
    private int source;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date bookTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
}