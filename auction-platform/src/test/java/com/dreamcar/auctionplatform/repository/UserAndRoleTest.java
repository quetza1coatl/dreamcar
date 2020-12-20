package com.dreamcar.auctionplatform.repository;

import com.dreamcar.auctionplatform.model.Role;
import com.dreamcar.auctionplatform.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserAndRoleTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DirtiesContext
    @Transactional
    void save(){
        Role customerRole = roleRepository.findByName("supplier");
        String email = "test@gmail.com";
        User u1 = new User(email, customerRole);
        User saved = userRepository.save(u1);
        Optional<User> optionalFromDb = userRepository.findById(saved.getId());
        assertThat(optionalFromDb.isPresent()).isEqualTo(true);
        assertThat(optionalFromDb.get().getEmail()).isEqualTo(email);
        assertThat(optionalFromDb.get().getRole()).isEqualTo(customerRole);
    }

    @Test
    void saveNotUniqueRole(){
        Role role = new Role("supplier");
        Assertions.assertThrows(DataIntegrityViolationException.class, () ->
            roleRepository.save(role)
        );
    }

    @Test
    @DirtiesContext
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
