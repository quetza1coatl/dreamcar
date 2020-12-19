package com.dreamcar.auctionplatform.repository;

import com.dreamcar.auctionplatform.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
