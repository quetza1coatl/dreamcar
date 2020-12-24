package com.dreamcar.auctionplatform.web;

import com.dreamcar.auctionplatform.dto.RequestDto;
import com.dreamcar.auctionplatform.dto.UserDto;
import com.dreamcar.auctionplatform.model.Request;
import com.dreamcar.auctionplatform.model.Role;
import com.dreamcar.auctionplatform.model.User;
import com.dreamcar.auctionplatform.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = RequestController.REST_URL)
@Slf4j
public class RequestController {
    static final String REST_URL = "/requests";
    private static final String LOG_TEMPLATE = "method : {}";
    private final RequestService requestService;

    //Mock: will be replaced by UserDetails object from SecurityContextHolder
    private UserDto user = new UserDto("customer@test.com", "customer");

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<RequestDto> getAllRequests() {
        log.info(LOG_TEMPLATE, "getAllRequests");
        return requestService.getAll(user);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Request> createRequest(@Valid @RequestBody RequestDto requestDto) {
        log.info(LOG_TEMPLATE, "createRequest");
        Request created = requestService.save(requestDto);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(path = "/feign", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createFeignRequest(@Valid @RequestBody RequestDto requestDto) {
        log.info(LOG_TEMPLATE, "createFeignRequest");
        Request created = requestService.save(requestDto);
        return REST_URL + "/" + created.getId();
    }

    @PostMapping(path="/updateRequest/{requestId}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public void updateRequest(@PathVariable Integer requestId, @RequestBody String expirationDate) {
        log.info(LOG_TEMPLATE, "updateRequest");
        requestService.update(requestId, expirationDate);
    }

}
