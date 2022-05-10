package la.com.jdbbank.service.jdbaccountservice.exception;

import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7316903070156188188L;
    SecVerifyRes userInfo;
    String message;
    String mgsDesc;
    /**
    public NotFoundException(String msg) {
        super(msg);
    }
    */

    public NotFoundException(SecVerifyRes userInfo, String message, String mgsDesc) {
        super();
        this.userInfo = userInfo;
        this.message = message;
        this.mgsDesc = mgsDesc;
    }
}