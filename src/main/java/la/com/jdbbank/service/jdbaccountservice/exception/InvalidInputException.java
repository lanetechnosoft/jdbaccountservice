package la.com.jdbbank.service.jdbaccountservice.exception;

import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;

public class InvalidInputException extends RuntimeException {
    private static final long serialVersionUID = 8956220829979476805L;
    SecVerifyRes userInfo;
    String message;
    String mgsDesc;
    /**
    public InvalidInputException(String message) {
        super(message);
    }*/

    public InvalidInputException(SecVerifyRes userInfo, String message, String mgsDesc) {
        //super(message);
        this.userInfo = userInfo;
        this.message = message;
        this.mgsDesc = mgsDesc;
    }

}
