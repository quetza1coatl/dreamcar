package com.dreamcar.auctionplatform.service;

import com.dreamcar.auctionplatform.dto.OfferDto;
import com.dreamcar.auctionplatform.dto.UserDto;
import com.dreamcar.auctionplatform.exceptions.EntityNotFoundException;
import com.dreamcar.auctionplatform.feignclient.Component;
import com.dreamcar.auctionplatform.feignclient.ComponentServiceFeignClient;
import com.dreamcar.auctionplatform.mapper.OfferMapper;
import com.dreamcar.auctionplatform.model.Offer;
import com.dreamcar.auctionplatform.model.OfferStatus;
import com.dreamcar.auctionplatform.model.Request;
import com.dreamcar.auctionplatform.model.User;
import com.dreamcar.auctionplatform.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class OfferService {
    private final OfferRepository offerRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final OfferStatusRepository offerStatusrepository;
    private final RequestStatusRepository requestStatusRepository;
    private final ComponentServiceFeignClient componentServiceFeignClient;
    private final OfferMapper offerMapper;

    public OfferService(
            OfferRepository offerRepository, RequestRepository requestRepository,
            UserRepository userRepository, OfferStatusRepository offerStatusrepository,
            RequestStatusRepository requestStatusRepository, ComponentServiceFeignClient componentServiceFeignClient,
            OfferMapper offerMapper
    ) {
        this.offerRepository = offerRepository;
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.offerStatusrepository = offerStatusrepository;
        this.requestStatusRepository = requestStatusRepository;
        this.componentServiceFeignClient = componentServiceFeignClient;
        this.offerMapper = offerMapper;
    }

    public Iterable<OfferDto> getAll(UserDto userDto) {
        return offerMapper.mapOffersToOfferDtos(userDto, offerRepository.findAll());
    }

    public Iterable<OfferDto> getOffersByRequestId(UserDto userDto, Integer requestId) {
        return offerMapper.mapOffersToOfferDtos(userDto, offerRepository.findByRequestId(requestId));
    }

    @Transactional
    public Offer save(OfferDto offerDto) {
        User supplier = userRepository.findByEmail(offerDto.getSupplierEmail());
        Optional<Request> request = requestRepository.findById(offerDto.getRequestId());
        OfferStatus offerStatus = offerStatusrepository.findByName(offerDto.getStatus());
        if (!request.isPresent()) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, Request.class.getSimpleName(), offerDto.getRequestId()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        Offer offer = new Offer(offerDto.getPrice(), offerDto.getDescription(), supplier, request.get(), offerStatus);
        return offerRepository.save(offer);
    }

    @Transactional
    public Offer update(Integer offerId, String price) {
        Optional<Offer> optionalOffer = offerRepository.findById(offerId);
        if (!optionalOffer.isPresent()) {
            String exceptionMessage = String.format(EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, Offer.class.getSimpleName(), offerId);
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }

        Offer offer = optionalOffer.get();
        offer.setPrice(BigDecimal.valueOf(Long.valueOf(price)));
        return offerRepository.save(offer);
    }

    @Transactional
    public void applyOffer(Integer offerId) {
        Optional<Offer> optionalOffer = offerRepository.findById(offerId);
        if (!optionalOffer.isPresent()) {
            String exceptionMessage = String.format(EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, Offer.class.getSimpleName(), offerId);
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }

        Offer offer = optionalOffer.get();
        offer.getRequest().getOffers().forEach(offerObject -> {
            if (offerObject.getId().equals(offerId)) {
                offerObject.setOfferStatus(offerStatusrepository.findByName("applied"));
            } else {
                offerObject.setOfferStatus(offerStatusrepository.findByName("rejected"));
            }
        });
        Component component = new Component(offer.getRequest().getPartName(), offer.getRequest().getQuantity());
        componentServiceFeignClient.getFeignComponent(component);
        offer.getRequest().setRequestStatus(requestStatusRepository.findByName("scheduled"));
        requestRepository.save(offer.getRequest());
    }
}
