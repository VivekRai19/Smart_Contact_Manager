package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.user;
import com.scm.helpers.AppConstants;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.helpers.helper;
import com.scm.repsitories.UserRepo;
import com.scm.services.EmailService;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

@Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public user saveUser(user user) {
        //user id: have to generate
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);
        //password encode
        //uder.setPassword(userId)
        user.setPassword(passwordEncoder.encode(user.getPassword()));



        //set the user role
          
         user.setRoleList(List.of(AppConstants.ROLE_USER));
        // logger.info(user.getProvider().toString());
        // user savedUser= userRepo.save(user);

        // String emailToken=UUID.randomUUID().toString();

        // String emailLink=Helper.getLinkforEmailVerification(emailToken);
        logger.info(user.getProvider().toString());
        String emailToken = UUID.randomUUID().toString();
        user.setEmailToken(emailToken);
        user savedUser = userRepo.save(user);
        String emailLink = helper.getLinkforEmailVerification(emailToken);
        emailService.sendEmail(savedUser.getEmail(), "Verify Account : Smart  Contact Manager", emailLink);
        return savedUser;
    }

    @Override
    public Optional<user> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<user> updatUser(user user) {
       user user2= userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
       user2.setName(user.getName());
       user2.setEmail(user.getEmail());
       user2.setPassword(user.getPassword());
       user2.setAbout(user.getAbout());
       user2.setPhoneNumber(user.getPhoneNumber());
       user2.setProfilePic(user.getProfilePic());
       user2.setEnabled(user.isEnabled());
       user2.setEmailVerifed(user.isEmailVerifed());
       user2.setPhoneVerifed(user.isPhoneVerifed());
       user2.setProvider(user.getProvider());
       user2.setProviderUserId(user.getProviderUserId());

       //save the user in data base
      user save= userRepo.save(user2);
      return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        user user2= userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userId) {
        user user2= userRepo.findById(userId).orElse(null);
        return user2!=null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        user user2= userRepo.findByEmail(email).orElse(null);
        return user2!=null ? true : false;
    }

    @Override
    public List<user> getAllUsers() {
      return userRepo.findAll();
    
    }

    @Override
    public user getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse( null
        );
    }

}
