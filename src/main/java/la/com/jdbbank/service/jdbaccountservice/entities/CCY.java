package la.com.jdbbank.service.jdbaccountservice.entities;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CCY {
    private String ccy;
    private BigDecimal limit_pm_amount,limit_pd_amount,fee_pm_amount;
}
