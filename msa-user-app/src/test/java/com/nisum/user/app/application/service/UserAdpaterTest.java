package com.nisum.user.app.application.service;

import com.nisum.user.app.application.output.port.UserDbPort;
import com.nisum.user.app.domain.User;
import com.nisum.user.app.infrastructure.input.adapter.rest.mapper.PhoneMapper;
import com.nisum.user.app.infrastructure.output.repository.entity.UserEntity;
import com.nisum.user.app.mock.MockData;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserAdpaterTest {
  @Mock private UserDbPort userDbPort;
  @Mock private PhoneMapper phoneMapper;

  @InjectMocks private UserAdapter userAdapter;


  @Test
  void shouldReturnUserWhenCallSave() {
    UserEntity userEmail = null;
    Mockito.when(userDbPort.findByEmail(Mockito.anyString())).thenReturn(userEmail);
    UserEntity userEntity = UserEntity.builder().created(LocalDateTime.now())
            .lastLogin(LocalDateTime.now())
            .id(UUID.randomUUID())
            .token("12345")
            .isActive(true)
            .build();
      Mockito.when(userDbPort.save(Mockito.any(User.class))).thenReturn(userEntity);
      Assertions.assertThat(userAdapter.save(MockData.getUser())).isNotNull();
  }

  @Test
  void shouldReturnUsersWhenCallFindAll() {
    List<UserEntity> users = List.of(MockData.getUserEntity(),MockData.getUserEntity());
    Mockito.when(userDbPort.findAll()).thenReturn(users);
    Assertions.assertThat(userAdapter.findAll()).isNotNull();
  }
}
