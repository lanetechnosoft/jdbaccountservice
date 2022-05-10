package la.com.jdbbank.service.jdbaccountservice.controller;

import la.com.jdbbank.service.jdbaccountservice.service.impl.FeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
    /*
    @Autowired
    FeignClientService feignClientService;
    @GetMapping("/")
    public ResponseEntity<?> get(){
        return ResponseEntity.ok().body(this.feignClientService.getUsers());
    }

     */
}
