package com.nisum.user.app.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User implements Serializable {

  private static final long serialVersionUID = -8122897908581270291L;

  UUID id;

  String name;

  String email;

  String password;

  LocalDateTime created;

  LocalDateTime modified;

  LocalDateTime lastLogin;

  String token;

  Boolean isActive;

  List<Phone> phones;
}
