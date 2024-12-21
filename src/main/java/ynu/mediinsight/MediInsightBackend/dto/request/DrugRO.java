package ynu.mediinsight.MediInsightBackend.dto.request;

import lombok.Data;

@Data
public class DrugRO {
    private String drug;
    private String ndcCode;
    private Integer dosage;
    private String dosageUnit;
    private String dosageForm;
}
