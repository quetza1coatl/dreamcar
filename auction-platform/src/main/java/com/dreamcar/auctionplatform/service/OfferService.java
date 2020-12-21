package com.dreamcar.auctionplatform.service;

import com.dreamcar.auctionplatform.dto.OfferDto;
import com.dreamcar.auctionplatform.exceptions.EntityNotFoundException;
import com.dreamcar.auctionplatform.model.*;
import com.dreamcar.auctionplatform.repository.OfferRepository;
import com.dreamcar.auctionplatform.repository.OfferStatusRepository;
import com.dreamcar.auctionplatform.repository.RequestRepository;
import com.dreamcar.auctionplatform.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Iterable<Offer> getAll(){
        return offerRepository.findAll();
    }

    @Transactional
    public Offer save(OfferDto offerDto) {
        Optional<User> optionalSupplier = userRepository.findById(offerDto.getSupplierId());
        Optional<Request> optionalRequest = requestRepository.findById(offerDto.getRequestId());
        Optional<OfferStatus> optionalOfferStatus = offerStatusrepository.findById(offerDto.getOfferStatusId());
        if (!optionalSupplier.isPresent()) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, User.class.getSimpleName(), offerDto.getSupplierId()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        if (!optionalRequest.isPresent()) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, Request.class.getSimpleName(), offerDto.getRequestId()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        if (!optionalOfferStatus.isPresent()) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, OfferStatus.class.getSimpleName(), offerDto.getOfferStatusId()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        Offer offer = new Offer(
                offerDto.getPrice(), offerDto.getDescription(),optionalSupplier.get(),
                optionalRequest.get(), optionalOfferStatus.get()
        );
        return offerRepository.save(offer);
    }
}
