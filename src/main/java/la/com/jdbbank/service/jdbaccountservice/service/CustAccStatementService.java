package la.com.jdbbank.service.jdbaccountservice.service;

import la.com.jdbbank.service.jdbaccountservice.entities.CustAccStatementRES;
import la.com.jdbbank.service.jdbaccountservice.repositories.CustAccStatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustAccStatementService {
    @Autowired
    private CustAccStatementRepository accStatementRepository;

    public Page<CustAccStatementRES> getAccStatement(String account_no, String start_date, String end_date, Pageable pageable) {
        return this.accStatementRepository.findCustAccStatement(account_no,start_date,end_date,pageable);
    }
}
