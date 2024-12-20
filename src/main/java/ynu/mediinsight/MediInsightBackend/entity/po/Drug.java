package ynu.mediinsight.MediInsightBackend.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("drugs")
public class Drug {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String atc;
    private String name;
    private Integer vid;
}
