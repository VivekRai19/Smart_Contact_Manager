package com.scm.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entities.user;
import com.scm.helpers.helper;
import com.scm.services.UserService;

@ControllerAdvice //ye har ek metthod ka leya chala ga 
public class RootController {

    private Logger logger= org.slf4j.LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @ModelAttribute
    public void addLoggedInUserOnformation(Model model, Authentication authentication){
        if (authentication == null) {
            return;
            
        }
        System.out.println("Adding logged in user information to the model");

        String username = helper.getEmailOfLoggedInUser(authentication);

        logger.info("User logged in: {}", username);
       
        // database se data ko fech: get user from db:
   
         user user =userService.getUserByEmail(username);
         System.out.println(user);
           System.out.println(user.getName());
           System.out.println(user.getEmail());
   
           model.addAttribute("loggedInUser", user);




    }

}
