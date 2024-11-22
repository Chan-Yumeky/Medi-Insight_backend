package ynu.mediinsight.MediInsightBackend.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorizeVO {
    String username;
    String role;
    String token;
    Date expire;
}
