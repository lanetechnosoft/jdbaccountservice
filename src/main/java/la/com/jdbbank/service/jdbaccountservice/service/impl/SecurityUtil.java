package la.com.jdbbank.service.jdbaccountservice.service.impl;


import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyReq;
import la.com.jdbbank.service.jdbaccountservice.common.SecVerifyRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "security-online", url = "${microservice.security.uri}")
public interface SecurityUtil {
    @PostMapping(value = "/security/verifyData", consumes = "application/json")
    public SecVerifyRes callVerifyUser(SecVerifyReq secVerifyReq);
}
