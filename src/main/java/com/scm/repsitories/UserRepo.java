package com.scm.repsitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.user;

@Repository
public interface UserRepo extends JpaRepository<user, String> {

    //extra methods db relatedoperaations
    //custom query mehods
    //custom finder method

    Optional<user>findByEmail(String email);
    Optional<user>findByEmailAndPassword(String email, String password);
    Optional<user>findByEmailToken(String id);

    
} 
