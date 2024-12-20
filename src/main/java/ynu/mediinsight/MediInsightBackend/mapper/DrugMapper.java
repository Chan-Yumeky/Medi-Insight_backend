package ynu.mediinsight.MediInsightBackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.mediinsight.MediInsightBackend.entity.po.Drug;

import java.util.List;

@Mapper
public interface DrugMapper extends BaseMapper<Drug> {
    @Select("select * from drugs where vid=#{vid}")
    List<Drug> selectAllDrugsByVid(int vid);
}
