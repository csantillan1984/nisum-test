package com.nisum.user.app.infrastructure.input.adapter.rest.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PhoneRequest implements Serializable {

  private static final long serialVersionUID = 1892351453435892271L;

  @NotBlank(message = "number field required")
  @Pattern(regexp = "^[0-9]+$", message = "number only must contain numbers")
  @Size(min = 5, max = 15, message = "number must be between 5 and 15 characters")
  String number;

  @NotBlank(message = "cityCode field required")
  @Pattern(regexp = "^[0-9]+$", message = "name only must contain numbers")
  @Size(min = 1, max = 5, message = "cityCode must be between 1 and 5 characters")
  String citycode;

  @NotBlank(message = "cityCode field required")
  @Pattern(regexp = "^[0-9]+$", message = "name only must contain numbers")
  @Size(min = 1, max = 5, message = "name must be between 1 and 5 characters")
  String contrycode;
}
