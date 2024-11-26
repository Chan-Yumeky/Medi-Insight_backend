package ynu.mediinsight.MediInsightBackend.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import ynu.mediinsight.MediInsightBackend.entity.BaseData;

import java.util.Date;

@Data
@TableName("account")
@AllArgsConstructor
public class Account implements BaseData {
    @TableId(type = IdType.AUTO)
    Integer id;
    @TableField("username")
    String username;
    @TableField("password")
    String password;
    @TableField("age")
    int age;
    @TableField("gender")
    String gender;
    @TableField("idCode")
    String idCode;
    @TableField("email")
    String email;
    @TableField("phone")
    String phone;
    @TableField("address")
    String address;
    @TableField("birthday")
    Date birthday;
    @TableField("department")
    String department;
    @TableField("isExpert")
    int isExpert;
}
