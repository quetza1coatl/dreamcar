package com.dreamcar.auctionplatform.repository;

import com.dreamcar.auctionplatform.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
