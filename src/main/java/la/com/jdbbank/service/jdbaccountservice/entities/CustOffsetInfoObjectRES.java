package la.com.jdbbank.service.jdbaccountservice.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder
public class CustOffsetInfoObjectRES {
    private List<CustAccOffsetInfoRES> acountinfo;
    //private List<FeeItem> feeLists;
    // todo: update new request Offset AccountInfo by ITNS on 20220509
    //private List<FeeStructureRes> feelists;
    //private List<ExchangeRateRes> exratelists;
    //private Boolean questoption;
    //private String prodcode;
}
