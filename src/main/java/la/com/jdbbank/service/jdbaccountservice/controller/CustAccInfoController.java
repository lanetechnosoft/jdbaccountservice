package la.com.jdbbank.service.jdbaccountservice.controller;

import com.sun.xml.bind.v2.TODO;
import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;
import la.com.jdbbank.service.jdbaccountservice.entities.*;
import la.com.jdbbank.service.jdbaccountservice.exception.NotFoundException;
import la.com.jdbbank.service.jdbaccountservice.exception.ServiceUnavailableException;
import la.com.jdbbank.service.jdbaccountservice.exception.UnauthorizedException;
import la.com.jdbbank.service.jdbaccountservice.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CustAccInfoController {
    @Autowired
    CustAccInfoService custAccInfoService;
    @Autowired
    private LogService logService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private FeeChargeService chargeService;
    @Autowired
    ExchangeRateService rateService;

    @Autowired
    Response response;
    private final Logger log = LoggerFactory.getLogger(CustAccInfoController.class);

    private ResponseEntity<?> buildResponseEntity(HttpStatus httpStatus, String message,
                                                  Object... data) {
        Boolean success = Boolean.valueOf((httpStatus == HttpStatus.OK));
        Object obj = (data.length > 0) ? data[0] : null;
        return new ResponseEntity(new ResponseMessageModel(success, message, obj), httpStatus);
    }
    @GetMapping("/get")
    public ResponseEntity<?> get(){
        // TODO: List Data From DB
        List<FeeStructureRes> list =this.chargeService.getAllFee("CNY","IBOB","EN1","001095822");
        return ResponseEntity.ok(list);
        //return ResponseEntity.ok(this.rateService.getAllRate());
    }

    @PostMapping({ "/useraccountinfo" })
    public ResponseEntity<?> post(@RequestBody CustAccInfoREQ body){
        //log.info("request "+body);

        // TODO: verify token, signHash and data
        SecVerifyRes secVerifyRes = new SecVerifyRes();
        // if verify fail
        try{
            secVerifyRes = securityService.verify(body.getRequestId(), body);
            System.out.println(secVerifyRes);
            if (!secVerifyRes.getStatus()) throw new UnauthorizedException(secVerifyRes, secVerifyRes.getMessage(), "Unauthorized");
        } catch ( Exception e){
            e.printStackTrace(System.err);
            logService.logError(secVerifyRes, e.getMessage(), e.getLocalizedMessage() );
            throw  new UnauthorizedException(secVerifyRes,"useraccountinfo",e.getLocalizedMessage());
        }
        // end Security service verify data for request
        // TODO:
        List<CustInfoObjectRES> accountinfo;
        try {
            // TODO: Check Question Security and Call Service AccountInfo
            boolean isTrue =  (secVerifyRes.getTxnPassword().equals("Y")) ? true: false;
            accountinfo = this.custAccInfoService.findbyaccount(body.getAccount_no(),isTrue,secVerifyRes.getFcProductCode(),secVerifyRes.getUserType());
        }catch (Exception e){
            //e.printStackTrace();
            logService.logError(secVerifyRes, secVerifyRes.getMessage(), e.getLocalizedMessage());
            throw new ServiceUnavailableException(secVerifyRes,"useraccountinfo",e.getLocalizedMessage());
            /***
            return buildResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.name(),
                    new Object[] { e.getMessage() });
             */
        }
        /**
         * When No Exception
         */

        if (accountinfo.get(0).getAcountinfo().size()==0){
            logService.logInfo(secVerifyRes,"retrieve record not found", accountinfo.toString());
            //return response.buildResponseEntity(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(),"retrieve record not found",secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { accountinfo });
            throw new NotFoundException(secVerifyRes, secVerifyRes.getMessage(), "retrieve record not found");
        }else{
            //int rows = accountinfo.get(0).getAcountinfo().size();
            //return response.buildResponseEntity(HttpStatus.OK, HttpStatus.OK.value(), (rows==1)? rows+" record found": rows+" records found", secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { accountinfo });
            return response.buildResponseEntity(secVerifyRes, "", new Object[] { accountinfo });
        }
    }

    // todo: update new request Offset AccountInfo by ITNS on 20220509
    @PostMapping({ "/useraccountoffsetinfo" })
    public ResponseEntity<?> postOffsetAc(@RequestBody CustAccInfoREQ body){
        //log.info("request "+body);

        // TODO: verify token, signHash and data
        SecVerifyRes secVerifyRes = new SecVerifyRes();
        // if verify fail
        try{
            secVerifyRes = securityService.verify(body.getRequestId(), body);
            System.out.println(secVerifyRes);
            if (!secVerifyRes.getStatus()) throw new UnauthorizedException(secVerifyRes, secVerifyRes.getMessage(), "Unauthorized");
        } catch ( Exception e){
            e.printStackTrace(System.err);
            logService.logError(secVerifyRes, e.getMessage(), e.getLocalizedMessage() );
            throw  new UnauthorizedException(secVerifyRes,"useraccountinfo",e.getLocalizedMessage());
        }
        // end Security service verify data for request
        // TODO:
        List<CustOffsetInfoObjectRES> accountinfo;
        try {
            // TODO: Check Question Security and Call Service AccountInfo
            boolean isTrue =  (secVerifyRes.getTxnPassword().equals("Y")) ? true: false;
            accountinfo = this.custAccInfoService.findbyaccountOffset(body.getAccount_no(),isTrue,secVerifyRes.getFcProductCode(),secVerifyRes.getUserType());
        }catch (Exception e){
            //e.printStackTrace();
            logService.logError(secVerifyRes, secVerifyRes.getMessage(), e.getLocalizedMessage());
            throw new ServiceUnavailableException(secVerifyRes,"useraccountinfo",e.getLocalizedMessage());
            /***
             return buildResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.name(),
             new Object[] { e.getMessage() });
             */
        }
        /**
         * When No Exception
         */

        if (accountinfo.get(0).getAcountinfo().size()==0){
            logService.logInfo(secVerifyRes,"retrieve record not found", accountinfo.toString());
            //return response.buildResponseEntity(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(),"retrieve record not found",secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { accountinfo });
            throw new NotFoundException(secVerifyRes, secVerifyRes.getMessage(), "retrieve record not found");
        }else{
            //int rows = accountinfo.get(0).getAcountinfo().size();
            //return response.buildResponseEntity(HttpStatus.OK, HttpStatus.OK.value(), (rows==1)? rows+" record found": rows+" records found", secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { accountinfo });
            return response.buildResponseEntity(secVerifyRes, "", new Object[] { accountinfo });
        }
    }
}
