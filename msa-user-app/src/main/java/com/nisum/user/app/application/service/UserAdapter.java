package com.nisum.user.app.application.service;

import com.nisum.user.app.application.input.port.UserPort;
import com.nisum.user.app.application.output.port.UserDbPort;
import com.nisum.user.app.domain.User;
import com.nisum.user.app.infrastructure.exception.UserException;
import com.nisum.user.app.infrastructure.input.adapter.rest.mapper.PhoneMapper;
import com.nisum.user.app.infrastructure.output.repository.entity.UserEntity;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAdapter implements UserPort {

  UserDbPort userDbPort;
  PhoneMapper phoneMapper;

  @Override
  public User save(User user) {
    validateEmail(user);
    UserEntity userEntity = userDbPort.save(user);
    return User.builder()
        .id(userEntity.getId())
        .created(userEntity.getCreated())
        .lastLogin(userEntity.getLastLogin())
        .token(userEntity.getToken())
        .isActive(userEntity.getIsActive())
        .build();
  }

  @Override
  public List<User> findAll() {
    return userDbPort.findAll().stream()
        .map(
            udb ->
                User.builder()
                    .isActive(udb.getIsActive())
                    .name(udb.getName())
                    .email(udb.getEmail())
                    .phones(
                        udb.getPhones().stream()
                            .map(p -> phoneMapper.mapPhoneEntityToPhoneDomain(p))
                            .toList())
                    .build())
        .toList();
  }

  private void validateEmail(User user) {
    UserEntity userEntity = userDbPort.findByEmail(user.getEmail());
    if (userEntity != null) {
      throw new UserException("email already registered", HttpStatus.BAD_REQUEST);
    }
  }
}
