package com.dreamcar.auctionplatform.service;

import com.dreamcar.auctionplatform.dto.OfferDto;
import com.dreamcar.auctionplatform.dto.UserDto;
import com.dreamcar.auctionplatform.exceptions.EntityNotFoundException;
import com.dreamcar.auctionplatform.model.*;
import com.dreamcar.auctionplatform.repository.OfferRepository;
import com.dreamcar.auctionplatform.repository.OfferStatusRepository;
import com.dreamcar.auctionplatform.repository.RequestRepository;
import com.dreamcar.auctionplatform.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class OfferService {
    private final OfferRepository offerRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final OfferStatusRepository offerStatusrepository;

    public OfferService(
            OfferRepository offerRepository, RequestRepository requestRepository,
            UserRepository userRepository, OfferStatusRepository offerStatusrepository
    ) {
        this.offerRepository = offerRepository;
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.offerStatusrepository = offerStatusrepository;
    }

    public Iterable<OfferDto> getAll(UserDto userDto){
        Iterable<Offer> offers = offerRepository.findAll();
        List<OfferDto> result = new LinkedList<>();
        offers.forEach(offer -> {
            OfferDto offerDto = new OfferDto();
            offerDto.setId(offer.getId());
            offerDto.setPrice(offer.getPrice());
            offerDto.setDescription(offer.getDescription());
            offerDto.setSupplierEmail(offer.getSupplier().getEmail());
            offerDto.setRequestId(offer.getRequest().getId());
            offerDto.setStatus(offer.getOfferStatus().getName());
            offerDto.setEditable(offer.getSupplier().getEmail().equals(userDto.getEmail()) && offer.getRequest().getRequestStatus().getName().equals("opened"));
            offerDto.setApplying(offer.getRequest().getCustomer().getEmail().equals(userDto.getEmail()) && offer.getRequest().getRequestStatus().getName().equals("closed"));
            result.add(offerDto);
        });
        return result;
    }

    @Transactional
    public Offer save(OfferDto offerDto) {
        User supplier = userRepository.findByEmail(offerDto.getSupplierEmail());
        Optional<Request> request = requestRepository.findById(offerDto.getRequestId());
        OfferStatus offerStatus = offerStatusrepository.findByName(offerDto.getStatus());
        if (supplier == null) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, User.class.getSimpleName(), offerDto.getSupplierEmail()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        if (!request.isPresent()) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, Request.class.getSimpleName(), offerDto.getRequestId()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        if (offerStatus == null) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, OfferStatus.class.getSimpleName(), offerDto.getStatus()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        Offer offer = new Offer(offerDto.getPrice(), offerDto.getDescription(),supplier, request.get(), offerStatus);
        return offerRepository.save(offer);
    }
}
