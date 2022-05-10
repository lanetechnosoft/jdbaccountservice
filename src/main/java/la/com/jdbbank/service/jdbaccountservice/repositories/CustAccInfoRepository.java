package la.com.jdbbank.service.jdbaccountservice.repositories;

import la.com.jdbbank.service.jdbaccountservice.entities.CustAccInfoRES;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustAccInfoRepository extends JpaRepository<CustAccInfoRES,Long> {
    @Query(value = "SELECT * FROM VW_IB_CUST_ACC_INFO WHERE 1=1 AND ACCOUNT_NO=?1", nativeQuery = true)
    List<CustAccInfoRES> findByAccountNo(String account_no);
}
