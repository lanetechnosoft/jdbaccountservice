package la.com.jdbbank.service.jdbaccountservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//@JsonDeserialize(builder = UserAccountByCIFRES.class)
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder
public class UserAccSumRES {
    @JsonIgnore
    private String IBANKIDUSER;
    @Id
    private String ACCOUNTTYPE;
    private BigDecimal SUM_ACCTYPE;
}
