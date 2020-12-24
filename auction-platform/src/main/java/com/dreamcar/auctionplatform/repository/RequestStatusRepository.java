package com.dreamcar.auctionplatform.repository;

import com.dreamcar.auctionplatform.model.RequestStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RequestStatusRepository extends CrudRepository<RequestStatus, Integer> {
    RequestStatus findByName(String name);
}
