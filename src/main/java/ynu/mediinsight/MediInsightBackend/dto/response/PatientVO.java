package ynu.mediinsight.MediInsightBackend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PatientVO {
    private Integer id;
    private String username;
    private int age;
    private String gender;
    private String phone;
    private String email;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
}
