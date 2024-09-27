package com.nisum.user.app.infrastructure.output.repository;

import com.nisum.user.app.infrastructure.output.repository.entity.UserEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
  UserEntity findByEmail(String email);
}
