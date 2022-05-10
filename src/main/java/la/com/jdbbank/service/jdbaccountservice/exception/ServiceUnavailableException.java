package la.com.jdbbank.service.jdbaccountservice.exception;

import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;


public class ServiceUnavailableException extends RuntimeException {
    private static final long serialVersionUID = 8956220829979476803L;

    SecVerifyRes userInfo;
    String message;
    String mgsDesc;

    private String serviceName;
    private String causeMessage;

    public ServiceUnavailableException(String message, String serviceName, String causeMessage) {
        super(message);
        this.serviceName = serviceName;
        this.causeMessage = causeMessage;
    }

    public ServiceUnavailableException(SecVerifyRes userInfo, String message, String mgsDesc) {
        this.userInfo = userInfo;
        this.message = message;
        this.mgsDesc = mgsDesc;
    }
}
