package com.nisum.user.app.infrastructure.output.repository.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "tbl_user")
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserEntity implements Serializable {

  private static final long serialVersionUID = 1307486169562229386L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id_user")
  UUID id;

  @Column(name = "name")
  String name;

  @Column(name = "email")
  String email;

  @Column(name = "password")
  String password;

  @Column(name = "created")
  LocalDateTime created;

  @Column(name = "modified")
  LocalDateTime modified;

  @Column(name = "last_login")
  LocalDateTime lastLogin;

  @Column(name = "token")
  String token;

  @Column(name = "is_active")
  Boolean isActive;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  List<PhoneEntity> phones;

  @PrePersist
  protected void onCreate() {
    this.created = LocalDateTime.now();
    this.lastLogin = created;
    this.isActive = Boolean.TRUE;
  }
}
