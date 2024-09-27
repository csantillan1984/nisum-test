package com.nisum.user.app.mock;

import com.nisum.user.app.domain.Phone;
import com.nisum.user.app.domain.User;
import com.nisum.user.app.infrastructure.output.repository.entity.PhoneEntity;
import com.nisum.user.app.infrastructure.output.repository.entity.UserEntity;

import java.util.Arrays;
import java.util.List;

public class MockData {

  public static final String URL_AUTH = "/auth/login?user=nisum";

  public static User getUser() {
    return User.builder()
        .name("Juan Perez")
        .email("juan@yahoo.com")
        .token("1236547")
        .password("mi password")
        .phones(getPhones())
        .build();
  }

  public static List<Phone> getPhones() {
    return List.of(Phone.builder().citycode("1").contrycode("3").number("022471025").build());
  }

  public static UserEntity getUserEntity() {
    return UserEntity.builder()
            .name("Juan Perezbd")
            .email("juan@hotmail.es")
            .token("123654755")
            .password("mi password1")
            .phones(getPhonesEntity())
            .build();
  }

  public static List<PhoneEntity> getPhonesEntity() {
    return List.of(PhoneEntity.builder().citycode("2").contrycode("4").number("022471026").build());
  }
}
