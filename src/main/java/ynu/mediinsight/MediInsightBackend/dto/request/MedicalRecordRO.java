package ynu.mediinsight.MediInsightBackend.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class MedicalRecordRO {
    private Integer id;
    private Integer did;
    private List<DiagnosisRO> diagnosis;
    private List<DrugRO> drug;
    private List<ProcedureRO> procedure;
}