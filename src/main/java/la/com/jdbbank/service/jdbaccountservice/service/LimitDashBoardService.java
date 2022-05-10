package la.com.jdbbank.service.jdbaccountservice.service;

import la.com.jdbbank.service.jdbaccountservice.entities.LimitDashBoard;
import la.com.jdbbank.service.jdbaccountservice.repositories.LimitDashBoardRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitDashBoardService {
    @Autowired
    private LimitDashBoardRepositoy dashBoardRepositoy;

    public List<LimitDashBoard> getAllLimit(String ibuser, String ccy){
        return this.dashBoardRepositoy.getAll(ibuser,ccy);
    }
}
