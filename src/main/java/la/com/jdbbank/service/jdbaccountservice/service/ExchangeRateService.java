package la.com.jdbbank.service.jdbaccountservice.service;

import la.com.jdbbank.service.jdbaccountservice.entities.ExchangeRateRes;
import la.com.jdbbank.service.jdbaccountservice.repositories.ExchangeRateReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateService {
    @Autowired
    private ExchangeRateReposity rateReposity;

    public List<ExchangeRateRes> getAllRate(){
        return this.rateReposity.findAll();
    }
}
