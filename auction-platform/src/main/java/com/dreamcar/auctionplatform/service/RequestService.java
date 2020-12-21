package com.dreamcar.auctionplatform.service;

import com.dreamcar.auctionplatform.dto.RequestDto;
import com.dreamcar.auctionplatform.exceptions.EntityNotFoundException;
import com.dreamcar.auctionplatform.model.Request;
import com.dreamcar.auctionplatform.model.RequestStatus;
import com.dreamcar.auctionplatform.model.User;
import com.dreamcar.auctionplatform.repository.RequestRepository;
import com.dreamcar.auctionplatform.repository.RequestStatusRepository;
import com.dreamcar.auctionplatform.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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


    public Iterable<Request> getAll(){
        return requestRepository.findAll();
    }

    @Transactional
    public Request save(RequestDto requestDto) {
        Optional<User> optionalCustomer = userRepository.findById(requestDto.getCustomerId());
        Optional<RequestStatus> optionalRequestStatus = requestStatusRepository.findById(requestDto.getRequestStatusId());
        if (!optionalCustomer.isPresent()) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, User.class.getSimpleName(), requestDto.getCustomerId()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        if (!optionalRequestStatus.isPresent()) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, RequestStatus.class.getSimpleName(), requestDto.getRequestStatusId()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }
        Request request = new Request(
                requestDto.getPartName(), requestDto.getQuantity(),requestDto.getDescription(),
                optionalCustomer.get(), optionalRequestStatus.get()
        );

        return requestRepository.save(request);
    }
}
