package com.nisum.user.app.infrastructure.input.adapter.rest.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse implements Serializable {

  private static final long serialVersionUID = 4217493279765843079L;

  UUID id;

  LocalDateTime created;

  LocalDateTime modified;

  LocalDateTime lastLogin;

  String token;

  Boolean isActive;
}
