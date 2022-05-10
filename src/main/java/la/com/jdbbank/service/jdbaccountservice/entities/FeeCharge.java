package la.com.jdbbank.service.jdbaccountservice.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder
public class FeeCharge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long SERVICECHARGEID;
    private String USERTYPE, PRODUCT_CODE, SOURCECCY;
    private BigDecimal FEE_AMOUNT,FEE_PERCENT,
    TRANSACTION_MIN_AMOUNT,
    TRANSACTION_MAX_AMOUNT,
    TOTAL_MONTHLY_AMOUNT,
    FEE_AMOUNT_MONTHLY,
    LIMIT_PER_DAY_AMOUNT = BigDecimal.ZERO;
}
