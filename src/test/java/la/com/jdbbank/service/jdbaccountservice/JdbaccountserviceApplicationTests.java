package la.com.jdbbank.service.jdbaccountservice;

import la.com.jdbbank.service.jdbaccountservice.repositories.FeeChargeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class JdbaccountserviceApplicationTests {

    @Autowired
    FeeChargeRepository chargeRepository;
    @Test
    void contextLoads() {
        /**
        Integer isTrue = this.chargeRepository.isSave(
                "12345678",
                "dddd",
                "fdfff",
                //"dfdfd",
                "erere554",
                "241545",
                "001FJCB144454",
                "type id",
                "001200012454",
                "Source account name",
                "LAK",
                "00120011zzzzzzz",
                "offset account name",
                "LAK",
                "email",
                "20xxxx",
                "JDB",
                "JDB",
                BigDecimal.ONE,
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(1500),
                BigDecimal.valueOf(2000),
                BigDecimal.valueOf(2),
                "cust no",
                "cust name",
                "A",
                "maker id",
                " auth id",
                "err  code",
                " err desc",
                " function name",
                "action id",
                "product code");
        System.out.println(isTrue);*/
    }
}
