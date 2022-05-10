package la.com.jdbbank.service.jdbaccountservice.exception;

import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;
import lombok.Data;

@Data
public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 895622082979476805L;
    SecVerifyRes userInfo;
    String message;
    String mgsDesc;
    /**
    public UnauthorizedException(String message) {
        super(message);
    }*/

    public UnauthorizedException(SecVerifyRes userInfo, String message, String mgsDesc) {
        super();
        this.userInfo = userInfo;
        this.message = message;
        this.mgsDesc = mgsDesc;
    }

    /*
    public UnauthorizedException(String message,String keydata) {
        super(message);
       // this.keydata=keydata;
    }
     */
}
