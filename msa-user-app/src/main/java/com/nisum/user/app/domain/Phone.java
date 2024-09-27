package com.nisum.user.app.domain;

import java.io.Serializable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Phone implements Serializable {

  private static final long serialVersionUID = 1892351453435892271L;

  String number;

  String citycode;

  String contrycode;

  User user;
}
