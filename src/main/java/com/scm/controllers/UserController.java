package com.scm.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.user;
import com.scm.helpers.helper;
import com.scm.services.UserService;



@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    



    //user dashboard page
      
    
    @RequestMapping(value = "/dashboard",method=RequestMethod.GET)
    public String userDashboard(){
        System.out.println("DASHBOARD");
        return "user/dashboard";
    }
    //user profile page
    @RequestMapping(value = "/profile",method=RequestMethod.GET)
    public String userProfile( Model model,Authentication authentication){
     String username = helper.getEmailOfLoggedInUser(authentication);

     logger.info("User logged in: {}", username);
     // database se data ko fech: get user from db:

      user user =userService.getUserByEmail(username);
        System.out.println(user.getName());
        System.out.println(user.getEmail());

        model.addAttribute("loggedInUser", user);
        return "user/profile";
    }
    //user add contact page 
    // user view contacts
    //user edit contact
    //user delete contact

}
