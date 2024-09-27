package com.nisum.user.app.infrastructure.input.adapter.rest.impl;

import com.nisum.user.app.application.service.JwtAdapter;
import com.nisum.user.app.infrastructure.input.adapter.rest.to.UserToken;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(
    value = "/auth",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
@RestController
public class AuthenticationController {

  JwtAdapter jwtAdapter;

  @GetMapping("login")
  public ResponseEntity<UserToken> login(@RequestParam("user") String user) {
    String token = jwtAdapter.getToken(user);
    return new ResponseEntity<>(UserToken.builder().token(token).user(user).build(), HttpStatus.OK);
  }
}
