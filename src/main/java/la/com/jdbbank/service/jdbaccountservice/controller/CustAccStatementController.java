package la.com.jdbbank.service.jdbaccountservice.controller;

import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;
import la.com.jdbbank.service.jdbaccountservice.entities.*;
import la.com.jdbbank.service.jdbaccountservice.exception.NotFoundException;
import la.com.jdbbank.service.jdbaccountservice.exception.ServiceUnavailableException;
import la.com.jdbbank.service.jdbaccountservice.exception.UnauthorizedException;
import la.com.jdbbank.service.jdbaccountservice.service.CustAccStatementService;
import la.com.jdbbank.service.jdbaccountservice.service.LogService;
import la.com.jdbbank.service.jdbaccountservice.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustAccStatementController {
    @Autowired
    CustAccStatementService custAccStatementService;

    @Autowired
    private LogService logService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    Response response;

    @Value("${page.size}")
    int pageSize;

    private final Logger log = LoggerFactory.getLogger(CustAccStatementController.class);

    private ResponseEntity<?> buildResponseEntity(HttpStatus httpStatus, String message,
                                                  Object... data) {
        Boolean success = Boolean.valueOf((httpStatus == HttpStatus.OK));
        Object obj = (data.length > 0) ? data[0] : null;
        return new ResponseEntity(new ResponseMessageModel(success, message, obj), httpStatus);
    }

    @PostMapping({ "/useraccountstmt" })
    public ResponseEntity<?> post(@RequestBody CustAccStatementREQ body) throws Exception {
        log.info("useraccountstmt request "+body);

        // verify token, signHash and data
        SecVerifyRes secVerifyRes = new SecVerifyRes();
        // if verify fail
        try{
            secVerifyRes = securityService.verify(body.getRequestId(), body);
            if (!secVerifyRes.getStatus()) throw new UnauthorizedException(secVerifyRes, secVerifyRes.getMessage(), "Unauthorized");
        } catch ( Exception e){
            e.printStackTrace(System.err);
            logService.logError(secVerifyRes, e.getMessage(), e.getLocalizedMessage());
            throw  new UnauthorizedException(secVerifyRes,"useraccountstmt",e.getLocalizedMessage());
        }
        // end Security service verify data for request

        try {
            /*
            * Date String Format
            * */

            String start_date = LocalDate.parse(body.getStart_date(), DateTimeFormatter.ISO_LOCAL_DATE).format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            String end_date = LocalDate.parse(body.getEnd_date(), DateTimeFormatter.ISO_LOCAL_DATE).format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            System.out.println("Start "+start_date);
            System.out.println("END "+end_date);
            /// get all persons by last name
            Pageable pageable = PageRequest.of(body.getPage_no(), pageSize);

            Page<CustAccStatementRES> accStatement = this.custAccStatementService.getAccStatement(body.getAccount_no(),start_date,end_date,pageable);
            //System.out.println(accStatement.getSize());
            //System.out.println(accStatement);

            if (accStatement.get().count()>0){

                List<PageCustAccStatementRES> resList = new ArrayList<>();
                PageCustAccStatementRES statementRES = new PageCustAccStatementRES();
                statementRES.setStarting_balance(accStatement.getContent().get(0).getOPENING_BAL());
                statementRES.setContent(accStatement.getContent());
                statementRES.setTotal_pages(accStatement.getTotalPages());
                statementRES.setTotal_elements(accStatement.getTotalElements());
                statementRES.setLast(accStatement.isLast());
                statementRES.setSize(accStatement.getSize());
                statementRES.setNumber(accStatement.getNumber());
                resList.add(statementRES);

                //return response.buildResponseEntity(HttpStatus.OK, HttpStatus.OK.value(), (resList.size()==1)? resList.size()+" record found": resList.size()+" records found", secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { resList });
                return response.buildResponseEntity(secVerifyRes, "", new Object[] { resList });
            }else{
                logService.logInfo(secVerifyRes, "retrieve record not found",accStatement.getContent().toString());
                return response.buildResponseEntity(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(),"retrieve record not found",secVerifyRes.getIbankIdUser(),body.getRequestId(),secVerifyRes.getKeyData(),new Object[] { accStatement.getContent() });
                //throw new NotFoundException(secVerifyRes, "retrieve record not found", accStatement.getContent().toString());

            }
        }catch (Exception e){
            e.printStackTrace();
            logService.logError(secVerifyRes, secVerifyRes.getMessage(), e.toString());
            //throw new ServiceUnavailableException(secVerifyRes.getKeyData(),"useraccountstmt",e.getLocalizedMessage());
            throw new ServiceUnavailableException(secVerifyRes,"useraccountstmt",e.getLocalizedMessage());
        }
    }
}
