package la.com.jdbbank.service.jdbaccountservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "VW_IB_CUST_ACC_INFO")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonPropertyOrder
public class CustAccInfoRES {
    private String BRN;
    private String BRANCh_NAME;
    private String CUSTOMER_NO;
    @Id
    private String ACCOUNT_NO;
    private String ACCOUNT_NAME;
    private String ADDRESS;
    private String ACCOUNT_CLASS;
    private String ACCOUNT_TYPE;
    private String CCY;
    private BigDecimal MIN_BALANCE;
    private BigDecimal ACY_OPENING_BAL;
    private BigDecimal LCY_OPENING_BAL;
    private BigDecimal ACY_AVL_BAL;
    private BigDecimal LCY_CURR_BALANCE;
    private BigDecimal ACY_CURR_BALANCE;
    private BigDecimal ACY_BLOCKED_AMOUNT;
    private String AC_STAT_DORMANT;
    private String AC_OPEN_DATE;
    private String RECORD_STAT;
    private int TENOR;
    private String INT_RATE;
    private String MATURITY_DATE;
    private BigDecimal MATURITY_AMOUNT;
    private String RENEW_DATE;
    private String CHEQUE_BOOK;
    private String OVERFRAFT_ALLOWED;
    private int NUMBER_OF_DUE_DATE,TOTAL_AMT_OVER_DUE;
    private BigDecimal TOTAL_PAID_PRINCIPAL,TOTAL_PAID_INTEREST,
    LAST_PAYMENT_PRINCIPAL,LAST_PAYMENT_INTEREST,NEXT_PAYMENT_PRINCIPAL,NEXT_PAYMENT_INTEREST;
    private String LAST_PAYMENT_DATE,NEXT_PAYMENT_DATE;


}
