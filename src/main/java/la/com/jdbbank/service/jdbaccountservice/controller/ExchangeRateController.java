package la.com.jdbbank.service.jdbaccountservice.controller;

import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;

import la.com.jdbbank.service.jdbaccountservice.entities.ExchangeRateRes;
import la.com.jdbbank.service.jdbaccountservice.entities.UserAccSumREQ;
import la.com.jdbbank.service.jdbaccountservice.exception.NotFoundException;
import la.com.jdbbank.service.jdbaccountservice.exception.ServiceUnavailableException;
import la.com.jdbbank.service.jdbaccountservice.exception.UnauthorizedException;
import la.com.jdbbank.service.jdbaccountservice.service.ExchangeRateService;
import la.com.jdbbank.service.jdbaccountservice.service.FeeChargeService;
import la.com.jdbbank.service.jdbaccountservice.service.LogService;
import la.com.jdbbank.service.jdbaccountservice.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ExchangeRateController {
    @Autowired
    private LogService logService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    ExchangeRateService rateService;

    @Autowired
    Response response;

    @PostMapping("/exchangerate")
    public ResponseEntity<?> post(@RequestBody UserAccSumREQ body){

        // TODO: verify token, signHash and data
        SecVerifyRes secVerifyRes = new SecVerifyRes();
        // if verify fail
        try{
            secVerifyRes = securityService.verify(body.getRequestId(), body);
            if (!secVerifyRes.getStatus()) throw new UnauthorizedException(secVerifyRes, secVerifyRes.getMessage(), "Unauthorized");
        } catch ( Exception e){
            //e.printStackTrace(System.err);
            logService.logError(secVerifyRes.getIbankIdUser(), body.getRequestId(), "exchangerate", "02", secVerifyRes.getMessage(), e.toString() );
            //throw  new UnauthorizedException(e.getLocalizedMessage());
            throw  new UnauthorizedException(secVerifyRes,"exchangerate",e.getLocalizedMessage());
        }
        // end Security service verify data for request
        // TODO:
        List<ExchangeRateRes> allRate;
        try {
            // TODO: Check Question Security and Call Service AccountInfo
            allRate = this.rateService.getAllRate();
        }catch (Exception e){
            //e.printStackTrace();
            logService.logError(secVerifyRes, secVerifyRes.getMessage(), e.getLocalizedMessage());
            throw new ServiceUnavailableException(secVerifyRes.getKeyData(),"exchangerate",e.getLocalizedMessage());
        }
        /**
         * When No Exception
         */

        if (allRate.size()==0){
            logService.logInfo(secVerifyRes, "retrieve record not found",allRate.toString());
            throw new NotFoundException(secVerifyRes, secVerifyRes.getMessage(), "retrieve record not found");
            //return response.buildResponseEntity(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(),"retrieve record not found",secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { allRate });
        }else{
            //int rows = allRate.size();
            //return response.buildResponseEntity(HttpStatus.OK, HttpStatus.OK.value(), (rows==1)? rows+" record found": rows+" records found", secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { allRate });
            return response.buildResponseEntity(secVerifyRes, "", new Object[] { allRate });
        }
    }
}
