package com.dreamcar.auctionplatform.web;

import com.dreamcar.auctionplatform.dto.RequestDto;
import com.dreamcar.auctionplatform.model.Request;
import com.dreamcar.auctionplatform.service.RequestService;
import lombok.extern.slf4j.Slf4j;
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

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Request> getAllRequests() {
        log.info(LOG_TEMPLATE, "getAllRequests");
        return requestService.getAll();
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

}
