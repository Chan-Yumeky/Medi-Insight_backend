package ynu.mediinsight.MediInsightBackend.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("role")
@AllArgsConstructor
public class Role {
    @TableId(type = IdType.AUTO)
    Integer id;
    @TableField("rolename")
    String rolename;
}
