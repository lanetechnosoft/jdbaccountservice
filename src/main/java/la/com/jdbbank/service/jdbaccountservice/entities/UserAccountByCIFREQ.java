package la.com.jdbbank.service.jdbaccountservice.entities;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class UserAccountByCIFREQ {
    @NotBlank(message = "request Id is required")
    private String requestId;
    //@NotBlank(message = "userid Id is required")
    //private String userid;
   @NotBlank(message = "acctype Id is required")
    private String acctype;
}
