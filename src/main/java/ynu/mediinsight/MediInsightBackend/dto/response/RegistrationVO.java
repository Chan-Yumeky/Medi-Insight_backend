package ynu.mediinsight.MediInsightBackend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ynu.mediinsight.MediInsightBackend.entity.po.Diagnosis;
import ynu.mediinsight.MediInsightBackend.entity.po.Drug;
import ynu.mediinsight.MediInsightBackend.entity.po.Procedure;

import java.util.Date;
import java.util.List;

@Data
public class RegistrationVO {
    private Integer id;
    private PatientVO patient;
    private DoctorVO doctor;
    private int status;
    private int source;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date bookTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    private List<Drug> drugs;
    private List<Procedure> procedures;
    private List<Diagnosis> diagnosis;
}
