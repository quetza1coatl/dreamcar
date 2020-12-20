package com.dreamcar.auctionplatform.repository;

import com.dreamcar.auctionplatform.model.Role;
import com.dreamcar.auctionplatform.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserAndRoleTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Test
    @DirtiesContext
    void save(){
        Role customerRole = roleRepository.findByName("supplier");
        User u1 = new User("test@gmail.com", customerRole);
        User saved = userRepository.save(u1);
        Optional<User> optionalFromDb = userRepository.findById(saved.getId());
        assertThat(optionalFromDb.isPresent()).isEqualTo(true);
        assertThat(optionalFromDb.get()).isEqualTo(saved);
    }

    @Test
    void saveNotUniqueRole(){
        Role role = new Role("supplier");
        Assertions.assertThrows(DataIntegrityViolationException.class, () ->
            roleRepository.save(role)
        );
    }

    @Test
    void saveNotUniqueUser() {
        Role roleFromDb = roleRepository.findByName("supplier");
        User user_1 = new User("test@gmail.com", roleFromDb);
        User user_2 = new User("test@gmail.com", roleFromDb);
        userRepository.save(user_1);
        Assertions.assertThrows(DataIntegrityViolationException.class, () ->
            userRepository.save(user_2)
        );
    }

}