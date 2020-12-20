package com.dreamcar.auctionplatform.repository;

import com.dreamcar.auctionplatform.model.OfferStatus;
import org.springframework.data.repository.CrudRepository;

public interface OfferStatusRepository extends CrudRepository<OfferStatus, Integer> {
    OfferStatus findByName(String name);
}
