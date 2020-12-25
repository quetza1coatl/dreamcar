package com.dreamcar.auctionplatform.service;

import com.dreamcar.auctionplatform.dto.UserDto;
import com.dreamcar.auctionplatform.exceptions.EntityNotFoundException;
import com.dreamcar.auctionplatform.model.Role;
import com.dreamcar.auctionplatform.model.User;
import com.dreamcar.auctionplatform.repository.RoleRepository;
import com.dreamcar.auctionplatform.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }


    public User save(UserDto userDto) {
        Role role = roleRepository.findByName(userDto.getRole());
        User user = new User(userDto.getEmail(), role);
        return userRepository.save(user);
    }
}
