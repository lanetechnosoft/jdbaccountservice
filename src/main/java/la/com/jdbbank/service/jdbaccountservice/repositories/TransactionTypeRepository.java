package la.com.jdbbank.service.jdbaccountservice.repositories;

import la.com.jdbbank.service.jdbaccountservice.entities.TransactionTypeRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeRes,String> {
    /*
    @Query(value="SELECT s.TRANSACTIONTYPEID,s.TRANSACTIONTYPENAME FROM VW_IB_USER_TRANSFER_ESLIP s WHERE 1=1  \n" +
            "AND exists(select 1 from vw_ib_user_acc_group_cif a where s.cifno=a.PRIMARYCIF AND a.IBANKIDUSER=?1)",nativeQuery=true)
     */
    @Query(value = "SELECT * FROM TABLE(PKG_JDB_ONLINE_BANKING.FN_IB_GET_TXN_LIST(?1))", nativeQuery = true)
    List<TransactionTypeRes> getTransactionList(String ibankuser);
}
