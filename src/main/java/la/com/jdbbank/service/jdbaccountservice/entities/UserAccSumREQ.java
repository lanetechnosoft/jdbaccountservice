package la.com.jdbbank.service.jdbaccountservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
public class UserAccSumREQ {
    @NotBlank(message = "request Id is required")
    private String requestId;
    //@NotBlank(message = "userid Id is required")
    //private String userid;
}
