package la.com.jdbbank.service.jdbaccountservice.repositories;

import la.com.jdbbank.service.jdbaccountservice.entities.UserAccSumRES;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAccSumRespository extends JpaRepository<UserAccSumRES,String> {
    @Query(value = "SELECT * FROM VW_IB_USER_ACC_CIF_SUM WHERE 1=1 AND IBANKIDUSER=?1", nativeQuery = true)
    List<UserAccSumRES> sumById(String userid);
}
