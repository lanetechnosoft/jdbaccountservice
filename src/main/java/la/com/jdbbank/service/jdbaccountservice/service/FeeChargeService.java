package la.com.jdbbank.service.jdbaccountservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import la.com.jdbbank.service.jdbaccountservice.entities.*;
import la.com.jdbbank.service.jdbaccountservice.repositories.FeeChargeRepository;
import la.com.jdbbank.service.jdbaccountservice.repositories.LimitDashBoardRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeeChargeService {
    @Autowired
    private FeeChargeRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    public List<FeeStructureRes> getAllFee(String currency,String prod,String usertype,String cust_no){
        // TODO: List Data From DB
        List<FeeCharge> list = this.repository.findAllByCcy(currency,prod,usertype);

        List<FeeStructureRes> structureResList = new ArrayList<>();
        List<FeeSubStructureRes> subStructureResList = new ArrayList<>();
        /***
        try {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
        // TODO: Check Count Data Result
        if (list.size()!=0){
            // TODO: Get Currency
            List<CCY> ccy = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                CCY ccy1 = new CCY();
                ccy1.setCcy(list.get(i).getSOURCECCY());
                ccy1.setLimit_pd_amount(list.get(i).getLIMIT_PER_DAY_AMOUNT());
                ccy1.setLimit_pm_amount(list.get(i).getTOTAL_MONTHLY_AMOUNT());
                ccy1.setFee_pm_amount(list.get(i).getFEE_AMOUNT_MONTHLY());
                ccy.add(ccy1);
                // TODO: Remove Duplicate Currency
                Object[] obj = ccy.toArray();
                for (Object o:  obj ) {
                    if (ccy.indexOf(o) != ccy.lastIndexOf(o)) {
                        ccy.remove(ccy.lastIndexOf(o));
                    }
                }
            }
            /***
            try {
                System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ccy));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            */
            System.out.println(ccy.size()+" "+ccy);
            // TODO: Loop Multi Currency
            for (int k = 0; k < ccy.size() ; k++) {
                String ccycode = ccy.get(k).getCcy();
                //if (!ccycode.equals(currency)) return  new ArrayList<FeeStructureRes>();
                FeeStructureRes structureRes = new FeeStructureRes();
                structureRes.setCcy(ccycode);
                structureRes.setTotal_pm_amount(ccy.get(k).getLimit_pm_amount());
                //structureRes.setTotal_pd_amount(this.repository.getLimitPERDay(usertype,currency,prod));
                structureRes.setTotal_pd_amount(ccy.get(k).getLimit_pd_amount());
                structureRes.setFee_pm_amt(ccy.get(k).getFee_pm_amount());
                // TODO: get Limit by Customer Number
                structureRes.setTotal_pd_amt_use(this.repository.getTotalAmtUsePD(cust_no,currency));
                structureRes.setTotal_pm_amt_use(this.repository.getTotalAmtUsePM(cust_no,currency));
                for (int i = 0; i < list.size() ; i++) {
                    // TODO: Loop Fee Structure
                    FeeSubStructureRes subStructureRes = new FeeSubStructureRes();
                    subStructureRes.setMin_amount(list.get(i).getTRANSACTION_MIN_AMOUNT());
                    subStructureRes.setMax_amount(list.get(i).getTRANSACTION_MAX_AMOUNT());
                    //subStructureRes.setLimit_pd_amount(list.get(i).getLIMIT_PER_DAY_AMOUNT());
                    //subStructureRes.setLimit_pm_amount(list.get(i).getTOTAL_MONTHLY_AMOUNT());
                    subStructureRes.setPercent(list.get(i).getFEE_PERCENT());
                    subStructureRes.setFeeamount(list.get(i).getFEE_AMOUNT());
                    subStructureResList.add(subStructureRes);
                    structureRes.setFeelists(subStructureResList);
                }
                structureResList.add(structureRes);
            }
        }
        return structureResList;
    }
}
