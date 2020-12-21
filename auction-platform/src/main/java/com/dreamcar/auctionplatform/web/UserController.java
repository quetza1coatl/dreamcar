package com.dreamcar.auctionplatform.web;

import com.dreamcar.auctionplatform.dto.UserDto;
import com.dreamcar.auctionplatform.model.User;
import com.dreamcar.auctionplatform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = UserController.REST_URL)
@Slf4j
public class UserController {
    static final String REST_URL = "/users";
    private static final String LOG_TEMPLATE = "method : {}";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> getAllUsers() {
        log.info(LOG_TEMPLATE, "getAllUsers");
        return userService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        log.info(LOG_TEMPLATE, "createUser");
        User created = userService.save(userDto);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
