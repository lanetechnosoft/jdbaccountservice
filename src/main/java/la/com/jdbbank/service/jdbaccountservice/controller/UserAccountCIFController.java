package la.com.jdbbank.service.jdbaccountservice.controller;

import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;
import la.com.jdbbank.service.jdbaccountservice.entities.*;
import la.com.jdbbank.service.jdbaccountservice.exception.ApiError;
import la.com.jdbbank.service.jdbaccountservice.exception.NotFoundException;
import la.com.jdbbank.service.jdbaccountservice.exception.ServiceUnavailableException;
import la.com.jdbbank.service.jdbaccountservice.exception.UnauthorizedException;
import la.com.jdbbank.service.jdbaccountservice.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController

public class UserAccountCIFController {
    @Autowired
    UserAccountCIFService accountCIFService;
    @Autowired
    UserAccSumService accSumService;


    @Autowired
    private LogService logService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    Response response;

    @Autowired
    LimitDashBoardService dashBoardService;

    private final Logger log = LoggerFactory.getLogger(UserAccountCIFController.class);

    private ResponseEntity<?> buildResponseEntity(HttpStatus httpStatus, String message,
                                                  Object... data) {
        Boolean success = Boolean.valueOf((httpStatus == HttpStatus.OK));
        Object obj = (data.length > 0) ? data[0] : null;
        return new ResponseEntity(new ResponseMessageModel(success, message, obj), httpStatus);
    }

    @PostMapping({ "/useraccountbycif" })
    public ResponseEntity<?> post(@Valid @RequestBody UserAccountByCIFREQ body) {
        log.info("useraccountbycif request "+body);

        // verify token, signHash and data
        SecVerifyRes secVerifyRes = new SecVerifyRes();
        // if verify fail
        try{
            secVerifyRes = securityService.verify(body.getRequestId(), body);
            System.out.println(secVerifyRes);
            if (!secVerifyRes.getStatus()) throw new UnauthorizedException(secVerifyRes, "useraccountbycif", "Unauthorized");
        } catch ( Exception e){
            e.printStackTrace(System.err);
            /*logService.logError(secVerifyRes.getIbankIdUser(), body.getRequestId(), "useraccountbycif", "02", secVerifyRes.getMessage(), e.toString() );
            throw  new UnauthorizedException(e.getLocalizedMessage());*/
            //logService.logError(secVerifyRes, e.getMessage(), e.getLocalizedMessage());
            throw  new UnauthorizedException(secVerifyRes,"useraccountbycif",e.getLocalizedMessage());
        }
        // end Security service verify data for request

        List<UserAccountByCIFRES> cifList;
        try {
            List<String> list = new ArrayList<>();
            if (body.getAcctype().equals("S")||body.getAcctype().equals("U") || body.getAcctype().equals("")){
                list.add("S");
                list.add("U");
            } else{
                list.add(body.getAcctype());
            }
            /**
             * Call Service By Bank User Id
             */
             cifList = this.accountCIFService.findbycif(secVerifyRes.getIbankIdUser(),list);

            if (cifList.size()==0){
                //log.info(cifList.toString());

                //logService.logInfo(secVerifyRes.getIbankIdUser(), body.getRequestId(),"useraccountbycif", "retrieve record not found",cifList.toString());
                //return response.buildResponseEntity(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(),"retrieve record not found",secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { cifList });

                logService.logInfo(secVerifyRes, "retrieve record not found",cifList.toString());
                //throw new NotFoundException(secVerifyRes, "retrieve record not found", "retrieve record not found");
                //throw new NotFoundException("retrieve record not found");
                return response.buildResponseEntity(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(),"retrieve record not found",secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { cifList });
            }

        }catch (Exception e){
            e.printStackTrace();
            //logService.logError(secVerifyRes, e.getMessage(), e.getLocalizedMessage() );
            throw new ServiceUnavailableException(secVerifyRes,e.getMessage(),e.getLocalizedMessage());
        }
        return response.buildResponseEntity(secVerifyRes, "", new Object[] { cifList });
        //return response.buildResponseEntity(HttpStatus.OK, HttpStatus.OK.value(), (cifList.size()==1)? cifList.size()+" record found": cifList.size()+" records found", secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),cifList);

    }

    @PostMapping({ "/useraccountsum" })
    public ResponseEntity<?> postUserAccSum(@RequestBody UserAccSumREQ body){
        //log.info("request "+body);

        // verify token, signHash and data
        SecVerifyRes secVerifyRes = new SecVerifyRes();
        // if verify fail
        try{
            secVerifyRes = securityService.verify(body.getRequestId(), body);
            System.out.println(secVerifyRes);
            if (!secVerifyRes.getStatus()) throw new UnauthorizedException(secVerifyRes, secVerifyRes.getMessage(), "Unauthorized");
        } catch ( Exception e){
            //e.printStackTrace(System.err);
            logService.logError(secVerifyRes, secVerifyRes.getMessage(), e.getLocalizedMessage());
            throw  new UnauthorizedException(secVerifyRes,"useraccountsum",e.getLocalizedMessage());
        }
        // end Security service verify data for request


        /**
         * Call Service then Check Response Exception
         */
        List<UserAccSumRES> sumbycif;
        try {
            sumbycif = this.accSumService.sumbycif(secVerifyRes.getIbankIdUser());
            System.out.println(sumbycif);
        }catch (Exception e){
            //e.printStackTrace();
            logService.logError(secVerifyRes, secVerifyRes.getMessage(), e.getLocalizedMessage());
            throw new ServiceUnavailableException(secVerifyRes,"useraccountsum",e.getLocalizedMessage());
            /*
            return buildResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.name(),
                    new Object[] { e.getMessage() });
             */
        }

        /**
         * When No Exception Error
         */

        if (sumbycif.size()==0){
            logService.logInfo(secVerifyRes, "retrieve record not found",sumbycif.toString());
            //throw new NotFoundException(secVerifyRes, secVerifyRes.getMessage(), "retrieve record not found");
            return response.buildResponseEntity(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(),"retrieve record not found",secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { sumbycif });
        }else{
            return response.buildResponseEntity(secVerifyRes, "", new Object[] { sumbycif });
            //return response.buildResponseEntity(HttpStatus.OK, HttpStatus.OK.value(), (sumbycif.size()==1)? sumbycif.size()+" record found": sumbycif.size()+" records found", secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { sumbycif });
        }
    }


    @PostMapping({ "/customerlimit" })
    public ResponseEntity<?> postLimitAmtTrn(@RequestBody UserAccSumREQ body){
        log.info("request "+body);

        // verify token, signHash and data
        SecVerifyRes secVerifyRes = new SecVerifyRes();
        // if verify fail
        try{
            secVerifyRes = securityService.verify(body.getRequestId(), body);
            System.out.println(secVerifyRes);
            if (!secVerifyRes.getStatus()) throw new UnauthorizedException(secVerifyRes, secVerifyRes.getMessage(), "Unauthorized");
        } catch ( Exception e){
            //e.printStackTrace(System.err);
            logService.logError(secVerifyRes, secVerifyRes.getMessage(), e.getLocalizedMessage());
            throw  new UnauthorizedException(secVerifyRes,"customerlimit",e.getLocalizedMessage());
        }
        // end Security service verify data for request


        /**
         * Call Service then Check Response Exception
         */
        List<LimitDashBoard> sumbycif;
        try {
            sumbycif =  this.dashBoardService.getAllLimit(secVerifyRes.getIbankIdUser(),"");
            System.out.println(sumbycif);
        }catch (Exception e){
            e.printStackTrace();
            logService.logError(secVerifyRes, secVerifyRes.getMessage(), e.getLocalizedMessage());
            throw new ServiceUnavailableException(secVerifyRes,"useraccountsum",e.getLocalizedMessage());
            /*
            return buildResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.name(),
                    new Object[] { e.getMessage() });
             */
        }

        /**
         * When No Exception Error
         */

        if (sumbycif.size()==0){
            logService.logInfo(secVerifyRes, "retrieve record not found",sumbycif.toString());
            throw new NotFoundException(secVerifyRes, secVerifyRes.getMessage(), "retrieve record not found");
            //return response.buildResponseEntity(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(),"retrieve record not found",secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { sumbycif });
        }else{
            return response.buildResponseEntity(secVerifyRes, "", new Object[] { sumbycif });
            //return response.buildResponseEntity(HttpStatus.OK, HttpStatus.OK.value(), (sumbycif.size()==1)? sumbycif.size()+" record found": sumbycif.size()+" records found", secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { sumbycif });
        }
    }
}
