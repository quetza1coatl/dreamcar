package com.dreamcar.auctionplatform.repository;

import com.dreamcar.auctionplatform.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class OfferRepositoryTest {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestStatusRepository requestStatusRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private OfferStatusRepository offerStatusRepository;
    @Autowired
    private OfferRepository offerRepository;

    @Test
    @DirtiesContext
    @Transactional
    void save(){
        Role roleFromDb = roleRepository.findByName("supplier");
        User user = new User("supplier@gmail.com", roleFromDb);

        User supplier = userRepository.save(user);
        RequestStatus requestStatus = requestStatusRepository.findByName("draft");
        Request request = new Request("brake pad", 42, supplier, requestStatus);
        Request savedRequest = requestRepository.save(request);
        OfferStatus appliedOfferStatus = offerStatusRepository.findByName("applied");

        Offer offer = new Offer(new BigDecimal("42.56"), supplier, savedRequest, appliedOfferStatus);
        Offer savedOffer = offerRepository.save(offer);

        Optional<Offer> optionalFromDb = offerRepository.findById(savedOffer.getId());
        assertThat(optionalFromDb.isPresent()).isEqualTo(true);
        assertThat(optionalFromDb.get().getOfferStatus()).isEqualTo(appliedOfferStatus);
        assertThat(optionalFromDb.get().getRequest()).isEqualTo(savedRequest);
        assertThat(optionalFromDb.get().getSupplier()).isEqualTo(supplier);
    }

}
