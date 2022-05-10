package la.com.jdbbank.service.jdbaccountservice.repositories;


import la.com.jdbbank.service.jdbaccountservice.entities.FeeCharge;
import la.com.jdbbank.service.jdbaccountservice.entities.FeeStructureRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

public interface FeeChargeRepository extends JpaRepository<FeeCharge, Long> {

    //@Query(value="SELECT  * FROM VW_IB_FEE_CHARGE WHERE SOURCECCY=?1 AND PRODUCT_CODE=?2 AND USERTYPE=?3 Order By 1",nativeQuery=true)
    @Query(value="SELECT * FROM TABLE(PKG_JDB_ONLINE_BANKING.FN_IB_FEE_CHARGE_LIST(?1,?2,?3))",nativeQuery=true)
    List<FeeCharge> findAllByCcy(String ccy,String prd,String usertype);

    @Query(value="SELECT FN_IB_CUST_TXN_AMT_PM(?1,?2) FROM DUAL",nativeQuery=true)
    BigDecimal getTotalAmtUsePM(String cust_no,String currency);

    @Query(value="SELECT FN_IB_CUST_TXN_AMT_PD(?1,?2) FROM DUAL",nativeQuery=true)
    BigDecimal getTotalAmtUsePD(String cust_no,String currency);

    @Query(value="SELECT FN_IB_LIMIT_AMT_PD(?1,?2,?3) FROM DUAL",nativeQuery=true)
    BigDecimal getLimitPERDay(String usertype,String currency,String prodCode);

/*

    @Modifying
    @Transactional
    @Query(value="CALL PRC_IB_LOG_TXN_TRANSFER(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12," +
            "?13,?14,?15,?16,?17,?18,?19,?20,?21,?22,?23,?24,?25,?26,?27,?28,?29,?30," +
            "?31,?32,?33,?34)",nativeQuery=true)
    Integer isSave(String pm_req_id,String pm_ibank_id_user,String pm_usertype,String pm_user_id,String pm_trn_id,
                   String pm_user_ref_no,String pm_ebank_ref_no,String pm_trn_ref_no,String pm_trn_type_id,String pm_src_acc,
                   String pm_src_name,String pm_src_ccy,String pm_offset_acc,String pm_offset_name,String pm_offset_ccy,
                   String pm_offset_email,String pm_offset_phone,String pm_bank_code,String pm_bank_type,BigDecimal pm_exc_rate,
                   BigDecimal pm_trn_amt,BigDecimal pm_lcy_trn_amt,BigDecimal pm_offset_amt,String pm_cust_no,String pm_cust_name,
                   String pm_txn_stat,String pm_maker_id,String pm_auth_id,String pm_err_code,String pm_err_desc,String pm_fnc_name,
                   String pm_action_id,String pm_prod_code,Integer _int);
*/

    @Modifying
    @Transactional
    @Procedure(procedureName="PRC_IB_LOG_TXN_TRANSFER",outputParameterName="output_result")
    Integer isSave(String pm_req_id,
                   String pm_ibank_id_user,
                   String pm_usertype, //String pm_trn_id,
                   String pm_user_ref_no,
                   String pm_ebank_ref_no,
                   String pm_trn_ref_no,
                   String pm_trn_type_id,
                   String pm_src_acc,
                   String pm_src_name,
                   String pm_src_ccy,
                   String pm_offset_acc,
                   String pm_offset_name,
                   String pm_offset_ccy,
                   String pm_offset_email,
                   String pm_offset_phone,
                   String pm_bank_code,
                   String pm_bank_type,
                   BigDecimal pm_exc_rate,
                   BigDecimal pm_trn_amt,
                   BigDecimal pm_lcy_trn_amt,
                   BigDecimal pm_offset_amt,
                   BigDecimal pm_fee_amt,
                   String pm_cust_no,
                   String pm_cust_name,
                   String pm_txn_stat,
                   String pm_maker_id,
                   String pm_auth_id,
                   String pm_err_code,
                   String pm_err_desc,
                   String pm_fnc_name,
                   String pm_action_id,
                   String pm_prod_code);
/*
    @Modifying
    @Transactional
    @Procedure(procedureName="PRC_IB_LOG_TXN_TRANSFER",outputParameterName="output_result")
    Integer isSave(String pm_req_id,String pm_ibank_id_user);*/

}
