package ynu.mediinsight.MediInsightBackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.mediinsight.MediInsightBackend.entity.po.Diagnosis;

import java.util.List;

@Mapper
public interface DiagnosisMapper extends BaseMapper<Diagnosis> {
    @Select("select * from diagnosis where vid=#{vid}")
    List<Diagnosis> selectDiagnosisByVid(int vid);
}
