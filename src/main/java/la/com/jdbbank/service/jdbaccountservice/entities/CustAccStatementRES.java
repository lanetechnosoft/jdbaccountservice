package la.com.jdbbank.service.jdbaccountservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder
public class CustAccStatementRES {
    @Id
    private String NO;
    //private String AC_BRANCH;
    //private String AC_NO;
    //private String AC_CCY;
    @JsonIgnore
    private String TRN_DT_CORE;
    private String TRN_DT;

    private  String TXN_DT_TIME;
    //@Id
    //private String AC_ENTRY_SR_NO;
    //@Id
    private String TRN_REF_NO;
    private String TRN_CODE;
    private String TRN_DESC;
    private String USER_ID;
    //private String AUTH_STAT;
    private BigDecimal CR_AMT;
    private BigDecimal DR_AMT;
    private BigDecimal CLOSING_BAL;
    private BigDecimal INTEREST;
    private BigDecimal LATE_CHARGE;
    @JsonIgnore
    private BigDecimal OPENING_BAL;
}
