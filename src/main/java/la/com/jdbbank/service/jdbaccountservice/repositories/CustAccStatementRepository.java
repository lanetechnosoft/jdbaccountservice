package la.com.jdbbank.service.jdbaccountservice.repositories;

import la.com.jdbbank.service.jdbaccountservice.entities.CustAccStatementRES;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
public interface CustAccStatementRepository extends JpaRepository<CustAccStatementRES,Long> {

    //SELECT * FROM (SELECT row_number() OVER (order by 1) NO,a.* FROM VW_ACC_STATEMENT WHERE 1=1 AND a.AC_NO=?1 AND TRN_DT BETWEEN ?2 AND ?3) WHERE NO BETWEEN ?4 AND ?5
    /*@Query(nativeQuery = true,value = "SELECT  * FROM (SELECT row_number() OVER (order by 1) NO,a.* FROM VW_ACC_STATEMENT a WHERE 1=1 AND a.AC_NO=?1 AND a.TRN_DT BETWEEN ?2 AND ?3) WHERE NO BETWEEN 1 AND 50",
            countQuery="")
    List<CustAccStatementRES> findCustAccStatement(String account_no, String start_date, String end_date);*/

    @Query(nativeQuery = true,value = "SELECT rownum as NO,a.*,nvl(fn_ib_opening_bal(?1, ?2), 0) AS opening_bal FROM VW_ACC_STATEMENT a WHERE a.AC_NO=?1 AND TRUNC(TRN_DT_CORE) BETWEEN ?2 AND ?3",
            countQuery ="select count(*) FROM VW_ACC_STATEMENT a WHERE a.AC_NO=?1 AND TRUNC(TRN_DT_CORE) BETWEEN ?2 AND ?3")
    Page<CustAccStatementRES> findCustAccStatement(String account_no, String start_date, String end_date,Pageable pageable);
}
