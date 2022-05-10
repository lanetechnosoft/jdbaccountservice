package la.com.jdbbank.service.jdbaccountservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import la.com.jdbbank.service.jdbaccountservice.entities.*;
import la.com.jdbbank.service.jdbaccountservice.repositories.CustAccInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustAccInfoService {
    @Autowired
    private FeeList feeList;
    @Autowired
    private FeeChargeService chargeService;
    @Autowired
    private ExchangeRateService rateService;

    @Autowired
    private CustAccInfoRepository custAccInfoRepository;

    /**
     *
     * @param account_no
     * @param option
     * @param prod
     * @param usertype
     * @return
     * @throws FileNotFoundException
     * @throws JsonProcessingException
     */
    public List<CustInfoObjectRES> findbyaccount(String account_no,Boolean option,String prod,String usertype) throws FileNotFoundException, JsonProcessingException {
        List<CustInfoObjectRES> objectRES = new ArrayList<>();
        CustInfoObjectRES infoObjectRES = new CustInfoObjectRES();

        /* Customer Account Details */
        List<CustAccInfoRES> list = this.custAccInfoRepository.findByAccountNo(account_no);
        infoObjectRES.setAcountinfo(list);
        infoObjectRES.setQuestoption(option);
        // TODO: Check FeeList
        if (list.size()!=0){
            // TODO: Assign Fee Lists
            //infoObjectRES.setFeeLists(new ArrayList<>());
            //System.out.println(list.get(0).getCCY());
            String ccy = list.get(0).getCCY();
            String cust_no = list.get(0).getCUSTOMER_NO();
            infoObjectRES.setFeelists(this.chargeService.getAllFee(ccy,prod,usertype,cust_no));
            infoObjectRES.setProdcode(prod);
        }else{
            infoObjectRES.setFeelists(new ArrayList<>());
        }

        // TODO: Check Rate List
        List<ExchangeRateRes> allRate = this.rateService.getAllRate();
        if (allRate.size()!=0){
            // TODO: Assign Exchange Rate Lists
            //infoObjectRES.setExratelists(new ArrayList<>());
            infoObjectRES.setExratelists(allRate);
        }else{
            infoObjectRES.setExratelists(new ArrayList<>());
        }

        objectRES.add(infoObjectRES);
        //return this.custAccInfoRepository.findByAccountNo(account_no);
        return objectRES;
    }
    // todo: update new request Offset AccountInfo by ITNS on 20220509
    /**
     *
     * @param account_no
     * @param option
     * @param prod
     * @param usertype
     * @return
     * @throws FileNotFoundException
     * @throws JsonProcessingException
     */
    public List<CustOffsetInfoObjectRES> findbyaccountOffset(String account_no, Boolean option, String prod, String usertype) throws FileNotFoundException, JsonProcessingException {
        List<CustOffsetInfoObjectRES> objectRES = new ArrayList<>();
        CustOffsetInfoObjectRES infoObjectRES = new CustOffsetInfoObjectRES();
        List<CustAccOffsetInfoRES> offsetInfoRES = new ArrayList<>();

        /* Customer Account Details */
        List<CustAccInfoRES> list = this.custAccInfoRepository.findByAccountNo(account_no);

        if (list.size()!=0) {
            CustAccOffsetInfoRES res = new CustAccOffsetInfoRES(list.get(0).getACCOUNT_NO(),
                    list.get(0).getACCOUNT_NAME(), list.get(0).getACCOUNT_CLASS(),
                    list.get(0).getACCOUNT_TYPE(), list.get(0).getCCY());
            offsetInfoRES.add(res);
        }

        infoObjectRES.setAcountinfo(offsetInfoRES);
        /**
            infoObjectRES.setQuestoption(option);
            // TODO: Check FeeList
            if (list.size()!=0){
                // TODO: Assign Fee Lists
                //infoObjectRES.setFeeLists(new ArrayList<>());
                //System.out.println(list.get(0).getCCY());
                String ccy = list.get(0).getCCY();
                String cust_no = list.get(0).getCUSTOMER_NO();
                infoObjectRES.setFeelists(this.chargeService.getAllFee(ccy,prod,usertype,cust_no));
                infoObjectRES.setProdcode(prod);
            }else{
                infoObjectRES.setFeelists(new ArrayList<>());
            }

            // TODO: Check Rate List
            List<ExchangeRateRes> allRate = this.rateService.getAllRate();
            if (allRate.size()!=0){
                // TODO: Assign Exchange Rate Lists
                //infoObjectRES.setExratelists(new ArrayList<>());
                infoObjectRES.setExratelists(allRate);
            }else{
                infoObjectRES.setExratelists(new ArrayList<>());
            }
            */
            objectRES.add(infoObjectRES);
            //return this.custAccInfoRepository.findByAccountNo(account_no);
            return objectRES;
        }
    }
