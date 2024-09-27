package com.nisum.user.app.infrastructure.input.adapter.rest.impl;

import com.nisum.user.app.application.service.JwtAdapter;
import com.nisum.user.app.application.service.UserAdapter;
import com.nisum.user.app.infrastructure.input.adapter.rest.mapper.UserMapper;
import com.nisum.user.app.infrastructure.input.adapter.rest.to.UserRequest;
import com.nisum.user.app.infrastructure.input.adapter.rest.to.UserResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(
    value = "/api/v1/user",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
@RestController
public class UserController {
  UserAdapter userAdapter;
  UserMapper userMapper;
  JwtAdapter jwtAdapter;

  @PostMapping
  public ResponseEntity<UserResponse> save(
      @Valid @RequestBody UserRequest request,
      @RequestHeader(value = "Authorization") String authorization) {
    log.info("init method save [{},{}]", () -> request, () -> authorization);
    request.setToken(jwtAdapter.getTokenOfHeader(authorization));
    UserResponse response =
        Optional.of(request)
            .map(userMapper::mapUserRequestToUserDomain)
            .map(userAdapter::save)
            .map(userMapper::mapUserToUserResponse)
            .get();
    log.info("end method save [{}]", () -> response);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<UserRequest>> findAll() {
    log.info("init method findAll");
    List<UserRequest> response = userMapper.mapUsersDomainToUsersRequest(userAdapter.findAll());
    log.info("end method findAll [{}]", () -> response);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
