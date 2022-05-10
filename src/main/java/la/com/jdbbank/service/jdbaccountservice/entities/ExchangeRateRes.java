package la.com.jdbbank.service.jdbaccountservice.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "VW_IB_EXCHANGE_RATE")
public class ExchangeRateRes {
    @Id
    //@Column(name = "CCY1")
    private String ccy1;
    @Column(name = "CCY2")
    private String ccy2;
    @Column(name = "BUY_RATE")
    private BigDecimal buy_rate;
    @Column(name = "SALE_RATE")
    private BigDecimal sale_rate;
    @Column(name = "RATE_DATE")
    private String rate_date;

}
