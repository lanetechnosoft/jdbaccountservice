package la.com.jdbbank.service.jdbaccountservice.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "VW_IB_USER_TRANSFER_ESLIP")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TransactionTypeRes {
    @Id
    private String TRANSACTIONTYPEID;
    private String TRANSACTIONTYPENAME;
}
