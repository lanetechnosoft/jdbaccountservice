package la.com.jdbbank.service.jdbaccountservice.controller;


import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;
import la.com.jdbbank.service.jdbaccountservice.entities.ResponseMessage;
import la.com.jdbbank.service.jdbaccountservice.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
public class Response {

    @Autowired
    private LogService logService;

    public ResponseEntity<?> buildResponseEntity(HttpStatus httpStatus, int resultCode, String message,String transUserId, String transactionId,String keyData, Object... data) {
        Boolean success = Boolean.valueOf((httpStatus == HttpStatus.OK));
        Object obj = (data.length > 0) ? data[0] : null;
        ResponseEntity<?> result = new ResponseEntity(new ResponseMessage(success, resultCode, message, keyData, obj), httpStatus);

        // log response data
        logService.logResponse(transUserId, transactionId, result);
        return result;
    }


    public ResponseEntity<?> buildResponseEntity(SecVerifyRes userInfo, String message, Object... data) {
        Object obj = (data.length > 0) ? data[0] : null;
        ResponseEntity<?> result = new ResponseEntity(new ResponseMessage(true, HttpStatus.OK.value(), "TRANSACTION SUCCESS " + message.toUpperCase(), userInfo.getKeyData(), obj), HttpStatus.OK);

        // user activity log response success
        logService.logResSucc(userInfo, data);

        return result;
    }

}
