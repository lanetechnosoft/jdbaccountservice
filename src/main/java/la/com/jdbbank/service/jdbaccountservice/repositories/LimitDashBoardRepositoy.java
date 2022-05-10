package la.com.jdbbank.service.jdbaccountservice.repositories;

import la.com.jdbbank.service.jdbaccountservice.entities.LimitDashBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LimitDashBoardRepositoy extends JpaRepository<LimitDashBoard,Long> {

    @Query(value="SELECT * FROM TABLE(PKG_JDB_IB_ACCOUNT.FN_IB_COMPARE_LIMIT(?1,?2))",nativeQuery=true)
    List<LimitDashBoard> getAll(String iuser, String ccy);

}
