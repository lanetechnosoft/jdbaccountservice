package la.com.jdbbank.service.jdbaccountservice.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class LimitDashBoard {
    @Id
    @Column(name = "LIMIT_PER_DAY_AMOUNT")
    private BigDecimal limitPD;
    @Column(name = "LIMIT_PER_DAY_AMT_USED")
    private BigDecimal limitPDUsed;
    @Column(name = "TOTAL_MONTHLY_AMOUNT")
    private BigDecimal limitPM;
    @Column(name = "TOTAL_MONTHLY_AMOUNT_USED")
    private BigDecimal limitPMUsed;
}
