package la.com.jdbbank.service.jdbaccountservice.common;

import lombok.Data;

@Data
public class LogInfo {
    /**
    private String transUserId;
    private String requestId;
    private String serviceName;
    private String functionName;
    private String transTitle;
    private String transStatus;
    private String transCode;
    private String transMessage;
    private String transDescription;
     */
    /**
     * update 20220118
     */

    private String transUserId;
    private String requestId;
    private String serviceName;
    private String functionName;
    private String transTitle; // USER, SYSTEM,
    private String transStatus; // SUCCESS, ERROR
    private String transCode; // SUCCESS: 200 , ERROR: 400, 500
    private String transMessage; // SUCCESS, SERVICE ERROR: SERVICE NOT AVAILABLE
    private String transDescription; // ERROR DESC
    private Object data; // object body request-response;
    private Object clientInfo; // object device info authentication only

    /**
    private String transUserId;
    private String requestId;
    private String serviceName;
    private String functionName;
    private String transTitle; // USER, SYSTEM,
    private String transStatus; // SUCCESS, ERROR
    private String transCode; // SUCCESS: 200 , ERROR: 400, 500
    private String transMessage; // SUCCESS, SERVICE ERROR: SERVICE NOT AVAILABLE
    private String transDescription; // ERROR DESC
    private Object data; // object body request-response;
    private Object clientInfo; // object device info authentication only
     */
}
