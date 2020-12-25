package com.dreamcar.auctionplatform.service;

import com.dreamcar.auctionplatform.dto.RequestDto;
import com.dreamcar.auctionplatform.dto.UserDto;
import com.dreamcar.auctionplatform.exceptions.EntityNotFoundException;
import com.dreamcar.auctionplatform.mapper.RequestMapper;
import com.dreamcar.auctionplatform.model.Request;
import com.dreamcar.auctionplatform.model.RequestStatus;
import com.dreamcar.auctionplatform.model.User;
import com.dreamcar.auctionplatform.repository.RequestRepository;
import com.dreamcar.auctionplatform.repository.RequestStatusRepository;
import com.dreamcar.auctionplatform.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final RequestStatusRepository requestStatusRepository;
    private final RequestMapper requestMapper;

    public RequestService(RequestRepository requestRepository, UserRepository userRepository, RequestStatusRepository requestStatusRepository, RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.requestStatusRepository = requestStatusRepository;
        this.requestMapper = requestMapper;
    }


    public Iterable<RequestDto> getAll(UserDto userDto) {
        return requestMapper.mapRequestsToRequestDtos(userDto, requestRepository.findAll());
    }

    @Transactional
    public Request save(RequestDto requestDto) {
        User customer = userRepository.findByEmail(requestDto.getCustomerEmail());
        RequestStatus status = requestStatusRepository.findByName(requestDto.getStatus());
        Request request = new Request(requestDto.getPartName(), requestDto.getQuantity(), requestDto.getDescription(), customer, status);
        return requestRepository.save(request);
    }

    @Transactional
    public Request update(Integer requestId, String expirationDate) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);
        if (!optionalRequest.isPresent()) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, Request.class.getSimpleName(), requestId
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
