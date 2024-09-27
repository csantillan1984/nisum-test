package com.nisum.user.app.infrastructure.input.adapter.rest.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nisum.user.app.infrastructure.util.PatternPassword;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest implements Serializable {

  private static final long serialVersionUID = -8122897908581270291L;

  @NotEmpty(message = "name field required")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "name must contain letters and spaces")
  @Size(min = 3, max = 100, message = "name must be between 3 and 100 characters")
  String name;

  @NotBlank(message = "email field required")
  @Email(
      regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
      flags = Pattern.Flag.CASE_INSENSITIVE,
      message = "incorrect email")
  @Size(min = 8, max = 100, message = "email must be between 3 and 100 characters")
  String email;

  @NotBlank(message = "password field required")
  @PatternPassword(message = "Invalid format")
  @Size(min = 5, max = 500, message = "name must be between 8 and 500 characters")
  String password;

  String token;

  @Valid List<PhoneRequest> phones;
}
