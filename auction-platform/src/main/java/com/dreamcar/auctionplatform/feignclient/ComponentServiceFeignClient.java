package com.dreamcar.auctionplatform.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "inventory-system")
public interface ComponentServiceFeignClient {
    @PostMapping(path = "/components/feign", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getFeignComponent(@Valid @RequestBody Component component);
}
