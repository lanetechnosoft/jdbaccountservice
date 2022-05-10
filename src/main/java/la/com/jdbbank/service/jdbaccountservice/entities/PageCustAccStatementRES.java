package la.com.jdbbank.service.jdbaccountservice.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder
public class PageCustAccStatementRES {
    private BigDecimal starting_balance=BigDecimal.ZERO;
    //private BigDecimal debit_total=BigDecimal.ZERO;
    //private BigDecimal credit_total=BigDecimal.ZERO;
    //private BigDecimal ending_balance=BigDecimal.ZERO;
    private List<CustAccStatementRES> content;
    private int total_pages;
    private long total_elements;
    private boolean last;
    private int size;
    private int number;
}
