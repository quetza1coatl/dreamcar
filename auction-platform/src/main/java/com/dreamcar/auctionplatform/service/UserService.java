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

import java.util.Optional;

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

    public Iterable<User> getAll(){
        return userRepository.findAll();
    }


    public User save(UserDto userDto){
        Optional<Role> optionalRole = roleRepository.findById(userDto.getRoleId());
        if (!optionalRole.isPresent()) {
            String exceptionMessage = String.format(
                    EntityNotFoundException.EXCEPTION_MESSAGE_FORMAT, Role.class.getSimpleName(), userDto.getRoleId()
            );
            log.error(exceptionMessage);
            throw new EntityNotFoundException(exceptionMessage);
        }

        User user = new User(userDto.getEmail(), optionalRole.get());
        return userRepository.save(user);
    }
}
