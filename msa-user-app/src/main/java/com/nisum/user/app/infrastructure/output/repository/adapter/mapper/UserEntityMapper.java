package com.nisum.user.app.infrastructure.output.repository.adapter.mapper;

import com.nisum.user.app.domain.User;
import com.nisum.user.app.infrastructure.output.repository.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
  UserEntity mapUserToUserEntity(User user);


}
