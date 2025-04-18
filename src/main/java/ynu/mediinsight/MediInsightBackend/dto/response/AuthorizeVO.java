package ynu.mediinsight.MediInsightBackend.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorizeVO {
    int id;
    String username;
    String role;
    String token;
    Date expire;
    
    // 新增字段
    Integer isExpert;
    String department;
}
