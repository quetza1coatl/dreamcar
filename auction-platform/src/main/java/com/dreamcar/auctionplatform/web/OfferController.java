package com.dreamcar.auctionplatform.web;

import com.dreamcar.auctionplatform.dto.OfferDto;
import com.dreamcar.auctionplatform.dto.UserDto;
import com.dreamcar.auctionplatform.model.Offer;
import com.dreamcar.auctionplatform.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = OfferController.REST_URL)
@Slf4j
public class OfferController {
    static final String REST_URL = "/offers";
    private static final String LOG_TEMPLATE = "method : {}";
    private final OfferService offerService;

    //Mock: will be replaced by UserDetails object from SecurityContextHolder
    private UserDto user = new UserDto("customer@test.com", "customer");

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<OfferDto> getAllOffers() {
        log.info(LOG_TEMPLATE, "getAllOffers");
        return offerService.getAll(user);
    }

    @GetMapping(path = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<OfferDto> getOffersByRequestId(@PathVariable Integer requestId) {
        log.info(LOG_TEMPLATE, "getOffersByRequestId");
        return offerService.getOffersByRequestId(user, requestId);
    }

    @GetMapping(path = "/applyOffer/{offerId}")
    public void applyOffer(@PathVariable Integer offerId) {
        log.info(LOG_TEMPLATE, "applyOffer");
        offerService.applyOffer(offerId);
    }


    @PostMapping(path = "/createOffer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody OfferDto offerDto) {
        log.info(LOG_TEMPLATE, "createOffer");
        offerDto.setSupplierEmail(user.getEmail());
        Offer created = offerService.save(offerDto);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(path = "/updateOffer/{offerId}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public void updateOffer(@PathVariable Integer offerId, @RequestBody String price) {
        log.info(LOG_TEMPLATE, "updateRequest");
        offerService.update(offerId, price);
    }
}
