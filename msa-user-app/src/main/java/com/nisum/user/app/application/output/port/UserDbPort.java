package com.nisum.user.app.application.output.port;

import com.nisum.user.app.domain.User;
import com.nisum.user.app.infrastructure.output.repository.entity.UserEntity;
import java.util.List;

public interface UserDbPort {

    UserEntity save(User user);
    UserEntity findByEmail(String email);
    List<UserEntity> findAll();
}
