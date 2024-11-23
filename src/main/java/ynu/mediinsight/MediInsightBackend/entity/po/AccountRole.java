package ynu.mediinsight.MediInsightBackend.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("account_role")
@AllArgsConstructor
public class AccountRole {
    @TableId
    Integer id;
    @TableField("uid")
    Integer uid;
    @TableField("rid")
    Integer rid;
}
