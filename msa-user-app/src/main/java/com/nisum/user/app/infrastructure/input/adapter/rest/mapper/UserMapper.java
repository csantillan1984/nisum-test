package com.nisum.user.app.infrastructure.input.adapter.rest.mapper;

import com.nisum.user.app.domain.User;
import com.nisum.user.app.infrastructure.input.adapter.rest.to.UserRequest;
import com.nisum.user.app.infrastructure.input.adapter.rest.to.UserResponse;
import com.nisum.user.app.infrastructure.output.repository.entity.UserEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapUserRequestToUserDomain(UserRequest request);
    UserResponse mapUserToUserResponse(User user);
    List<User> mapUsersEntityToUsersDomain(List<UserEntity> userEntities);
    List<UserRequest> mapUsersDomainToUsersRequest(List<User> users);

}
