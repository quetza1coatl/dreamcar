package com.dreamcar.inventorysystem.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "auction-platform")
public interface RequestServiceFeignClient {

    @PostMapping(path = "/requests/feign", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createRequest(@Valid @RequestBody RequestDto requestDto);
}
