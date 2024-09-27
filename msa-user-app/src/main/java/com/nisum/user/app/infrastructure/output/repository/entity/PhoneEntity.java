package com.nisum.user.app.infrastructure.output.repository.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "tbl_phone")
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PhoneEntity implements Serializable {

  private static final long serialVersionUID = -8470536034024813474L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_phone" )
  Long id;

  @Column(name = "number")
  String number;

  @Column(name = "city_code")
  String citycode;

  @Column(name = "country_code")
  String contrycode;

  @ManyToOne
  @JoinColumn(name = "id_user", nullable = false)
  UserEntity user;
}
