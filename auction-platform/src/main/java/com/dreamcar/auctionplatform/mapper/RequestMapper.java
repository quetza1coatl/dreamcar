package com.dreamcar.auctionplatform.mapper;

import com.dreamcar.auctionplatform.dto.RequestDto;
import com.dreamcar.auctionplatform.dto.UserDto;
import com.dreamcar.auctionplatform.model.Request;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RequestMapper {
    public Iterable<RequestDto> mapRequestsToRequestDtos(UserDto userDto, Iterable<Request> requests) {
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
}
