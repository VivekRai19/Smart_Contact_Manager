package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.user;

public interface UserService {
    user saveUser(user user);

    Optional<user> getUserById(String id);

    Optional<user> updatUser(user user);

    void deleteUser(String id);

    boolean isUserExist(String userId);

    boolean isUserExistByEmail(String email);

    List<user>getAllUsers();
     user getUserByEmail(String email);
    //add more method here if needed i user service [logic]

}
