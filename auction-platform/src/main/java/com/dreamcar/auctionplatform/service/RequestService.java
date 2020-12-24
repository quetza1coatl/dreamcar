package com.dreamcar.auctionplatform.service;

import com.dreamcar.auctionplatform.dto.RequestDto;
import com.dreamcar.auctionplatform.dto.UserDto;
import com.dreamcar.auctionplatform.exceptions.EntityNotFoundException;
import com.dreamcar.auctionplatform.model.Request;
import com.dreamcar.auctionplatform.model.RequestStatus;
import com.dreamcar.auctionplatform.model.User;
import com.dreamcar.auctionplatform.repository.RequestRepository;
import com.dreamcar.auctionplatform.repository.RequestStatusRepository;
import com.dreamcar.auctionplatform.repository.UserRepository;
import com.sun.jmx.snmp.Timestamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
@Transactional(readOnly = true)
public class RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final RequestStatusRepository requestStatusRepository;

    public RequestService(RequestRepository requestRepository, UserRepository userRepository, RequestStatusRepository requestStatusRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.requestStatusRepository = requestStatusRepository;
    }


    public Iterable<RequestDto> getAll(UserDto userDto){
        Iterable<Request> requests = requestRepository.findAll();
        List<RequestDto> result = new LinkedList<>();
        requests.forEach(request -> {
            RequestDto requestDto = new RequestDto();
            requestDto.setId(request.getId());
            requestDto.setPartName(request.getPartName());
            requestDto.setQuantity(request.getQuantity());
            requestDto.setDescription(request.getDescription());
            requestDto.setCustomerEmail(request.getCustomer().getEmail());
            requestDto.setCreationDate(request.getCreationDate().toString());
            requestDto.setExpirationDate(request.getExpirationDate() == null ? null : request.getExpirationDate().toString());
            requestDto.setStatus(request.getRequestStatus().getName());
            requestDto.setEditable(request.getCustomer().getEmail().equals(userDto.getEmail()) && request.getRequestStatus().getName().equals("draft"));
            requestDto.setOfferCreated(userDto.getRole().equals("supplier") && request.getOffers().stream().noneMatch(offer -> offer.getSupplier().getEmail().equals(userDto.getEmail())));
            result.add(requestDto);
        });
        return result;
    }

    @Transactional
    public Request save(RequestDto requestDto) {
        User customer = userRepository.findByEmail(requestDto.getCustomerEmail());
        RequestStatus status = requestStatusRepository.findByName(requestDto.getStatus());
        if (customer == null) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, User.class.getSimpleName(), requestDto.getCustomerEmail()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        if (status == null) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, RequestStatus.class.getSimpleName(), requestDto.getStatus()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        Request request = new Request(
                requestDto.getPartName(), requestDto.getQuantity(),requestDto.getDescription(), customer, status
        );

        return requestRepository.save(request);
    }

    @Transactional
    public Request update(Integer requestId, String expirationDate) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);
        if (!optionalRequest.isPresent()) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, RequestStatus.class.getSimpleName(), requestId
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        Request request = optionalRequest.get();
        request.setExpirationDate(new Date(Long.parseLong(expirationDate)).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        request.setRequestStatus(requestStatusRepository.findByName("opened"));
        return requestRepository.save(request);
    }
}
