package la.com.jdbbank.service.jdbaccountservice.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FeeStructureRes {
    private String ccy;
    private BigDecimal total_pd_amount,total_pd_amt_use,total_pm_amount,total_pm_amt_use,fee_pm_amt= BigDecimal.ZERO;
    private List<FeeSubStructureRes> feelists;
}
