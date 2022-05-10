package la.com.jdbbank.service.jdbaccountservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyReq;
import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;
import la.com.jdbbank.service.jdbaccountservice.service.impl.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SecurityService {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    private LogService logService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    HttpServletRequest headers;

    @Autowired
    ObjectMapper jsonMapper;

    public SecVerifyRes verify(String requestId, String token, String signHash, String TxnType, String data) {

        // set data
        SecVerifyReq secVerifyReq = new SecVerifyReq();
        SecVerifyRes result = new SecVerifyRes();
        try {

            secVerifyReq.setRequestId(requestId);
//            secVerifyReq.setServiceName(appName);
            secVerifyReq.setToken(token);
            secVerifyReq.setSingHash(signHash);
            secVerifyReq.setTxnTypeId(TxnType);
            secVerifyReq.setData(data);

            result = securityUtil.callVerifyUser(secVerifyReq);

        } catch (Exception e) {
            e.printStackTrace();
            logService.logError(result, "SERVICE NOT AVAILABLE", e.toString());
        }
        return result;
    }

    public SecVerifyRes verify(String requestId,Object data) {
        SecVerifyRes result = new SecVerifyRes();
        try {
            //System.out.println("Data "+jsonMapper.writeValueAsString(data));
            //System.out.println("header "+headers.getHeader("TxnTypeId"));
            // ser data
            SecVerifyReq secVerifyReq = new SecVerifyReq();
            secVerifyReq.setRequestId(requestId);
            secVerifyReq.setToken(headers.getHeader("Authorization"));
            secVerifyReq.setSingHash(headers.getHeader("SignedHash"));
            secVerifyReq.setTxnTypeId(headers.getHeader("TxnTypeId"));
            secVerifyReq.setData(jsonMapper.writeValueAsString(data));
            secVerifyReq.setServiceName(appName);
            secVerifyReq.setClientInfo(headers.getHeader("User-Agent"));

            result = securityUtil.callVerifyUser(secVerifyReq);

        } catch (Exception e) {
            e.printStackTrace(System.err);
            //logService.logError("", requestId, "Security Verify data", "99", "service Unavailable", e.toString());
            logService.logError(result, "SERVICE NOT AVAILABLE", e.toString());
        }
        return result;
    }
}
