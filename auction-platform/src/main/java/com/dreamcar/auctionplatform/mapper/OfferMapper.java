package com.dreamcar.auctionplatform.mapper;

import com.dreamcar.auctionplatform.dto.OfferDto;
import com.dreamcar.auctionplatform.dto.UserDto;
import com.dreamcar.auctionplatform.model.Offer;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OfferMapper {

    public Iterable<OfferDto> mapOffersToOfferDtos(UserDto userDto, Iterable<Offer> offers) {
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
}
