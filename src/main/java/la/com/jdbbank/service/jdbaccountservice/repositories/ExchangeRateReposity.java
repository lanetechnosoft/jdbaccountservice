package la.com.jdbbank.service.jdbaccountservice.repositories;

import la.com.jdbbank.service.jdbaccountservice.entities.ExchangeRateRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExchangeRateReposity extends JpaRepository<ExchangeRateRes,Long> {
    @Query(value="SELECT * FROM VW_IB_EXCHANGE_RATE WHERE CCY1=?1",nativeQuery=true)
    List<ExchangeRateRes> findAllRate(String ccy);
}
