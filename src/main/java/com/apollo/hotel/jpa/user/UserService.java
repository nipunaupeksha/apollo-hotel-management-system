package com.apollo.hotel.jpa.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User createUser(CreateUserParameters parameters);

    User createAdministrator(CreateUserParameters parameters);

    Page<User> getUsers(Pageable pageable);

    boolean userWithEmailExists(Email email);

    User editUser(UserId userId, EditUserParameters parameters);

    Optional<User> getUser(UserId userId);

    void deleteUser(UserId userId);
}

