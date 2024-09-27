package com.nisum.user.app.application.input.port;

import com.nisum.user.app.domain.User;
import java.util.List;

public interface UserPort {

    User save(User user);
    List<User> findAll();

}
