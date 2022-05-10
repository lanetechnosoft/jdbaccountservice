package la.com.jdbbank.service.jdbaccountservice.service;

import la.com.jdbbank.service.jdbaccountservice.entities.UserAccSumRES;
import la.com.jdbbank.service.jdbaccountservice.repositories.UserAccSumRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccSumService {
    @Autowired
    UserAccSumRespository userAccSumRespository;
    public List<UserAccSumRES> sumbycif(String userid){
        return  this.userAccSumRespository.sumById(userid);
    }
}
