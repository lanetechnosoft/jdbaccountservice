package la.com.jdbbank.service.jdbaccountservice.service;

import la.com.jdbbank.service.jdbaccountservice.common.LogInfo;
import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;
import la.com.jdbbank.service.jdbaccountservice.service.impl.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    private LogUtil logUtil;

    /***
     * TODO: New Log Transaction
     * @param userInfo
     * @param data
     */
    public void logResSucc(SecVerifyRes userInfo, Object data) {
        LogInfo logInfo = new LogInfo();
        logInfo.setTransUserId(userInfo.getIbankIdUser());
        logInfo.setRequestId(userInfo.getRequestId());
        logInfo.setServiceName(appName);
        logInfo.setFunctionName(userInfo.getTxnTypeId());
        logInfo.setTransTitle("USER");
        logInfo.setTransStatus("SUCCESS");
        logInfo.setTransCode(Integer.toString(HttpStatus.OK.value()));
        logInfo.setTransMessage("TRANSACTION SUCCESS");
        logInfo.setTransDescription("RESPONSE TRANSACTION SUCCESS");
        logInfo.setData(data);
        logInfo.setClientInfo("");
        String result = logUtil.callAddInfo(logInfo);
    }
    public void logResErr(SecVerifyRes userInfo, String statusCode, String mgs,  String mgsDetail) {
        LogInfo logInfo = new LogInfo();
        logInfo.setTransUserId(userInfo.getIbankIdUser());
        logInfo.setRequestId(userInfo.getRequestId());
        logInfo.setServiceName(appName);
        logInfo.setFunctionName(userInfo.getTxnTypeId());
        logInfo.setTransTitle("USER");
        logInfo.setTransStatus("ERROR");
        logInfo.setTransCode(statusCode);
        logInfo.setTransMessage(mgs);
        logInfo.setTransDescription(mgsDetail);
        logInfo.setData("");
        logInfo.setClientInfo("");
        String result = logUtil.callAddInfo(logInfo);
    }
    public void logInfo(SecVerifyRes userInfo, String transMessage, String transDescription) {
        LogInfo logInfo = new LogInfo();
        logInfo.setTransUserId(userInfo.getIbankIdUser());
        logInfo.setRequestId(userInfo.getRequestId());
        logInfo.setServiceName(appName);
        logInfo.setFunctionName(userInfo.getTxnTypeId());
        logInfo.setTransTitle("SYSTEM");
        logInfo.setTransStatus("SUCCESS");
        logInfo.setTransCode(Integer.toString(HttpStatus.OK.value()));
        logInfo.setTransMessage(transMessage);
        logInfo.setTransDescription(transDescription);
        logInfo.setData("");
        logInfo.setClientInfo("");
        //System.out.println("LogInfoRequest "+logInfo);
        String result = logUtil.callAddInfo(logInfo);
        //System.out.println(result);
    }
    public void logError(SecVerifyRes userInfo, String errorMsg, String errorDes) {
        LogInfo logInfo = new LogInfo();
        logInfo.setTransUserId(userInfo.getIbankIdUser());
        logInfo.setRequestId(userInfo.getRequestId());
        logInfo.setServiceName(appName);
        logInfo.setFunctionName(userInfo.getTxnTypeId());
        logInfo.setTransTitle("SYSTEM");
        logInfo.setTransStatus("ERROR");
        logInfo.setTransCode(Integer.toString(HttpStatus.SERVICE_UNAVAILABLE.value()));
        logInfo.setTransMessage(errorMsg);
        logInfo.setTransDescription(errorDes);
        logInfo.setData("");
        logInfo.setClientInfo("");
        String result = logUtil.callAddInfo(logInfo);
    }

    /***
     * TODO: Old Log Service
     * @param transUserId
     * @param requestId
     * @param data
     */
    public void logResponse(String transUserId, String requestId, Object data) {
        LogInfo logInfo = new LogInfo();
        logInfo.setTransUserId(transUserId);
        logInfo.setRequestId(requestId);
        logInfo.setServiceName("Request-Response");
        logInfo.setFunctionName("Response Data");
        logInfo.setTransTitle("Info");
        logInfo.setTransStatus("Success");
        logInfo.setTransCode("00");
        logInfo.setTransMessage("Response has success");
        logInfo.setTransDescription(data.toString());
        String result = logUtil.callAddInfo(logInfo);
    }
    public void logInfo(String transUserId, String requestId, String functionName, String transMessage, String transDescription) {
        LogInfo logInfo = new LogInfo();
        logInfo.setTransUserId(transUserId);
        logInfo.setRequestId(requestId);
        logInfo.setServiceName(appName);
        logInfo.setFunctionName(functionName);
        logInfo.setTransTitle("Info");
        logInfo.setTransStatus("Success");
        logInfo.setTransCode("00");
        logInfo.setTransMessage(transMessage);
        logInfo.setTransDescription(transDescription);
        String result = logUtil.callAddInfo(logInfo);
    }
    public void logError(String transUserId, String requestId, String functionName, String transCode, String transMessage, String transDescription) {
        LogInfo logInfo = new LogInfo();
        logInfo.setTransUserId(transUserId);
        logInfo.setRequestId(requestId);
        logInfo.setServiceName(appName);
        logInfo.setFunctionName(functionName);
        logInfo.setTransTitle("Info");
        logInfo.setTransStatus("Error");
        logInfo.setTransCode(transCode);
        logInfo.setTransMessage(transMessage);
        logInfo.setTransDescription(transDescription);
        String result = logUtil.callAddInfo(logInfo);
    }

}
