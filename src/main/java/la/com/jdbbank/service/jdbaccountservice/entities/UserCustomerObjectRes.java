package la.com.jdbbank.service.jdbaccountservice.entities;

import lombok.Data;

import java.util.List;

@Data
public class UserCustomerObjectRes {
    private List<UserAccountByCIFRES> UserAccountByCifList;
    private List<TransactionTypeRes> TransactionTypeList;
}
