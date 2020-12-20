package com.dreamcar.auctionplatform.repository;

import com.dreamcar.auctionplatform.model.Request;
import com.dreamcar.auctionplatform.model.RequestStatus;
import com.dreamcar.auctionplatform.model.Role;
import com.dreamcar.auctionplatform.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RequestRepositoryTest {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestStatusRepository requestStatusRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DirtiesContext
    @Transactional
    void save(){
        Role roleFromDb = roleRepository.findByName("customer");
        String email = "test@gmail.com";
        User user = new User(email, roleFromDb);
        User customer = userRepository.save(user);
        RequestStatus requestStatus = requestStatusRepository.findByName("draft");
        Request request = new Request("brake pad", 42, customer, requestStatus);
        Request saved = requestRepository.save(request);

        Optional<Request> optionalFromDb = requestRepository.findById(saved.getId());
        assertThat(optionalFromDb.isPresent()).isEqualTo(true);
        assertThat(optionalFromDb.get().getRequestStatus()).isEqualTo(requestStatus);
        assertThat(optionalFromDb.get().getPartName()).isEqualTo("brake pad");
        assertThat(optionalFromDb.get().getCustomer()).isEqualTo(customer);

    }

    @Test
    @DirtiesContext
    void updateExpirationDateAndStatus(){
        Role roleFromDb = roleRepository.findByName("supplier");
        User user = new User("test@gmail.com", roleFromDb);
        User savedUser = userRepository.save(user);
        RequestStatus closedStatus = requestStatusRepository.findByName("closed");
        Request request = new Request("brake pad", 42, savedUser, closedStatus);
        Request draft = requestRepository.save(request);

        RequestStatus requestStatusUpdated = requestStatusRepository.findByName("opened");
        LocalDateTime requestExpirationDateUpdated = draft.getCreationDate().plusDays(10);
        draft.setRequestStatus(requestStatusUpdated);
        draft.setExpirationDate(requestExpirationDateUpdated);
        requestRepository.save(draft);

        Optional<Request> optionalOpened = requestRepository.findById(draft.getId());
        assertThat(optionalOpened.isPresent()).isEqualTo(true);
        Request opened = optionalOpened.get();
        assertThat(opened.getId()).isEqualTo(draft.getId());
        assertThat(opened.getRequestStatus()).isEqualTo(requestStatusUpdated);
        assertThat(opened.getExpirationDate()).isEqualTo(requestExpirationDateUpdated);
    }

}
