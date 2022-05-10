package la.com.jdbbank.service.jdbaccountservice.common;

import lombok.Data;

@Data
public class SecVerifyRes {
    /**
     * update 20220118
     */
    private Boolean status;
    private String message;
    private String requestId;
    private String txnTypeId;
    private int userStatus;
    private String userId;
    private String ibankIdUser;
    private String keyData;
    private String phoneNo;
    private String email;
    private String userType;
    private String txnPassword;
    private String optChanel;
    private String sqId;
    private String questionName;
    private String answerQuestion;
    private String cifNo;
    private String fcProductCode;
    private String fcGlCharge;

    /**
    private Boolean status;
    private String message;
    private String userId;
    private String phoneNo;
    private String email;
    private String ibankIdUser;
    private String keyData;
     */
    /**
    private Boolean status;
    private String message;
    private String userId;
    private String ibankIdUser;
    private String phoneNo;
    private String email;
    private String userType;
    private String txnPassword;
    private String optChanel;
    private String fcProductCode;
    private String fcGlCharge;
    private String keyData;
    private String requestStatus;
    private String requestId;
    private String txnTypeId;
     */
}
