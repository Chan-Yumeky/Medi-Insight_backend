package ynu.mediinsight.MediInsightBackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.mediinsight.MediInsightBackend.entity.po.Procedure;

import java.util.List;

@Mapper
public interface ProcedureMapper extends BaseMapper<Procedure> {
    @Select("select * from `procedure` where vid=#{vid}")
    List<Procedure> selectProcedureByVid(int vid);
}
