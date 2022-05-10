package la.com.jdbbank.service.jdbaccountservice.service;

import la.com.jdbbank.service.jdbaccountservice.entities.TransactionTypeRes;
import la.com.jdbbank.service.jdbaccountservice.entities.UserAccSumRES;
import la.com.jdbbank.service.jdbaccountservice.entities.UserAccountByCIFRES;
import la.com.jdbbank.service.jdbaccountservice.entities.UserCustomerObjectRes;
import la.com.jdbbank.service.jdbaccountservice.repositories.TransactionTypeRepository;
import la.com.jdbbank.service.jdbaccountservice.repositories.UserAccountCIFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class UserAccountCIFService {
    @Autowired
    private UserAccountCIFRepository CIF_REPOSITORY;
    @Autowired
    private TransactionTypeRepository typeRepository;
    
    public List<UserAccountByCIFRES> findbycif(String userid, List<String> acctype){
        return  this.CIF_REPOSITORY.findById(userid,acctype);
    }

    public List<UserCustomerObjectRes> findbycifNo(String userid, List<String> acctype){
        List<UserCustomerObjectRes> list = new ArrayList<>();
        UserCustomerObjectRes objectRes = new UserCustomerObjectRes();
        objectRes.setTransactionTypeList(this.typeRepository.getTransactionList(userid));
        objectRes.setUserAccountByCifList(this.CIF_REPOSITORY.findById(userid,acctype));
        list.add(objectRes);
        return list;
    }
}
