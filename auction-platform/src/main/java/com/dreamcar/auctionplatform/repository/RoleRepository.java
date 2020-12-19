package com.dreamcar.auctionplatform.repository;

import com.dreamcar.auctionplatform.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
