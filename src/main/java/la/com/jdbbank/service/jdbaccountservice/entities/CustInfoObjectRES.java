package la.com.jdbbank.service.jdbaccountservice.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder
public class CustInfoObjectRES {
    private List<CustAccInfoRES> acountinfo;
    //private List<FeeItem> feeLists;
    private List<FeeStructureRes> feelists;
    private List<ExchangeRateRes> exratelists;
    private Boolean questoption;
    private String prodcode;
}
