package com.dreamcar.auctionplatform.repository;

import com.dreamcar.auctionplatform.model.Offer;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, Integer> {
}
