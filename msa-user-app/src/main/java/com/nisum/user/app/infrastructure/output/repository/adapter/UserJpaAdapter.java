package com.nisum.user.app.infrastructure.output.repository.adapter;

import com.nisum.user.app.application.output.port.UserDbPort;
import com.nisum.user.app.domain.User;
import com.nisum.user.app.infrastructure.output.repository.UserRepository;
import com.nisum.user.app.infrastructure.output.repository.adapter.mapper.UserEntityMapper;
import com.nisum.user.app.infrastructure.output.repository.entity.UserEntity;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserJpaAdapter implements UserDbPort {
  UserRepository userRepository;
  UserEntityMapper userMapper;

  @Override
  @Transactional
  public UserEntity save(User user) {

    UserEntity entity = userMapper.mapUserToUserEntity(user);
    entity.getPhones().forEach(phoneEntity -> phoneEntity.setUser(entity));
    return userRepository.save(entity);
  }

  @Override
  public UserEntity findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }
}
