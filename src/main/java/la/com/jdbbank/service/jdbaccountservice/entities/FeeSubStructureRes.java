package la.com.jdbbank.service.jdbaccountservice.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FeeSubStructureRes {
    private BigDecimal min_amount,max_amount,percent,feeamount;
}
