package la.com.jdbbank.service.jdbaccountservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "VW_IB_USER_ACC_GROUP_CIF")
//@Table(name = "VW_IB_USER_ACC_GROUP_CIF",schema = "ibdbuat")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//@JsonDeserialize(builder = UserAccountByCIFRES.class)
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonPropertyOrder
public class UserAccountByCIFRES implements Serializable {
    @JsonIgnore
    private String IBANKIDUSER;
    private String USERTYPE;
    private String FIRSTNAME;
    private String LASTNAME;
    private String CIFNO;
    private String CIFNAME;
    private String ACCOUNTTYPE;
    private String ACCOUNTTYPEDESC;
    private String ACCOUNTCLASS;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ACCOUNTNO;
    private String ACCOUNTNAME;
    private String ACCCOUNTCCY;
    private String TRANSACTIONSTATUS;
    private String SIGN_BY_AUTHLEVEL1_IBANKIDUSER;
    private String SIGN_BY_AUTHLEVEL2_IBANKIDUSER;
    private String DELETESTATUS;
    private String PRIMARYCIF;
    private String ACCOUNT_RECORD_STATUS;
    private BigDecimal AMOUNT_BLOCK,AVAILABLE_BANLANCE,CURRENT_BALANCE,LCY_AVL_BANLANCE;
}
