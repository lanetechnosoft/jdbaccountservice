package la.com.jdbbank.service.jdbaccountservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

//import javax.validation.constraints.NotBlank;

@Data
public class CustAccInfoREQ {
    @NotBlank(message = "request Id is required")
    private String requestId;
    @NotBlank(message = "account_no is required")
    private String account_no;
}
