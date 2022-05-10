package la.com.jdbbank.service.jdbaccountservice.common;

import lombok.Data;

@Data
public class SecVerifyReq {
    private String requestId;
    private String token;
    private String singHash;
    private String txnTypeId;
    private String data;
    private String clientInfo;
    private String serviceName;
}
