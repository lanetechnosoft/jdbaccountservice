package la.com.jdbbank.service.jdbaccountservice.entities;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustAccStatementREQ {
    @NotBlank(message = "request Id is required")
    private String requestId;
    @NotBlank(message = "account_no is required")
    private String account_no;
    @NotBlank(message = "start_date is required")
    private String start_date;
    @NotBlank(message = "end_date is required")
    private String end_date;
    @NotBlank(message = "page_no is required")
    private int page_no;
}
