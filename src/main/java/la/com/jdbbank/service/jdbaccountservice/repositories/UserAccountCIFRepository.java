package la.com.jdbbank.service.jdbaccountservice.repositories;

import la.com.jdbbank.service.jdbaccountservice.entities.UserAccSumRES;
import la.com.jdbbank.service.jdbaccountservice.entities.UserAccountByCIFRES;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface UserAccountCIFRepository extends JpaRepository<UserAccountByCIFRES, Long> {

    @Query(value = "SELECT * FROM VW_IB_USER_ACC_GROUP_CIF WHERE 1=1 AND IBANKIDUSER=?1 AND ACCOUNTTYPE IN (?2)", nativeQuery = true)
    //@Query(value = "SELECT * FROM TABLE(PKG_JDB_ONLINE_BANKING.FN_IB_USER_ACC_BY_CIF(?1,?2))", nativeQuery = true)
    List<UserAccountByCIFRES> findById(String userid, List<String> acctype);
}
