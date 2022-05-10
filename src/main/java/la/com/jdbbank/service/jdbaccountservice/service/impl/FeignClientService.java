package la.com.jdbbank.service.jdbaccountservice.service.impl;

import la.com.jdbbank.service.jdbaccountservice.config.FeignConfig;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-client", url="https://jsonplaceholder.typicode.com", configuration = FeignConfig.class)
public interface FeignClientService {
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    String getUsers();
}
