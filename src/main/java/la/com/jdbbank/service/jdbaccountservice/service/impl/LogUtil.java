package la.com.jdbbank.service.jdbaccountservice.service.impl;

import la.com.jdbbank.service.jdbaccountservice.common.LogInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "log-online", url = "${microservice.log.uri}")
public interface LogUtil {
    @PostMapping(value = "/logInfo/add", consumes = "application/json")
    public String callAddInfo(LogInfo logInfo);
}

