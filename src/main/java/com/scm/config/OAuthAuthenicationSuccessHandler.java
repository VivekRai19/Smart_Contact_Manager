package com.scm.config;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.entities.Providers;
import com.scm.entities.user;
import com.scm.helpers.AppConstants;
import com.scm.repsitories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {


    Logger logger=LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);
    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
           logger.info("OAuthAuthenicationSuccessHandler");
            //identify the provider
            var oauth2AuthenicationToken=(OAuth2AuthenticationToken)authentication;

          String authorizedClientRegistrationId=  oauth2AuthenicationToken.getAuthorizedClientRegistrationId();

          logger.info(authorizedClientRegistrationId);


         var oauthUser= (DefaultOAuth2User)authentication.getPrincipal();

         oauthUser.getAttributes().forEach((key, value)->{
          logger.info(key+":"+value);
         });

         user user=new user();
         user.setUserId(UUID.randomUUID().toString());
         user.setRoleList(List.of(AppConstants.ROLE_USER));
         user.setEmailVerifed(true);
         user.setEnabled(true);
         user.setPassword("dummy");



          if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
             //google
            //google attribute
            user.setEmail(oauthUser.getAttribute("email").toString());
            user.setProfilePic(oauthUser.getAttribute("picture").toString());
            user.setName(oauthUser.getAttribute("name").toString());
            user.setProviderUserId(oauthUser.getName());
            user.setProvider(Providers.GOGGLE);
            user.setAbout("This account is created by Google");



          }
           else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
            //github 
            //github attribute
            String email=oauthUser.getAttribute("email")!=null? oauthUser.getAttribute("email").toString() : oauthUser.getAttribute("login").toString()+"@gmail.com";
            String picture = oauthUser.getAttribute("avatar_url").toString();
            String name= oauthUser.getAttribute("login").toString();
            String providerUserId = oauthUser.getName();

            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderUserId(providerUserId);
            user.setProvider(Providers.GITHUB);
            user.setAbout("This account is created by Github");

           }
           else if (authorizedClientRegistrationId.equalsIgnoreCase("facbook")) {
            
           }else{
            logger.info("OAuthAuthenicattionSuccessHandler:Unknown provider");
           }

            


            //facebook
            //facebook attribute



          /*   DefaultOAuth2User user=(DefaultOAuth2User)authentication.getPrincipal();
             
            // logger.info((user.getName()));

            // user.getAttributes().forEach((key,value)->{
            //  logger.info("{} => {}",key,value);
            // });

            // logger.info(user.getAuthorities().toString());
            String email=user.getAttribute("email").toString();
            String name=user.getAttribute("name").toString();
            String picture=user.getAttribute("picture").toString();

            /// create user and save in database
            user user1=new user();
            user1.setEmail(email);
            user1.setName(name);
            user1.setProfilePic(picture);
            user1.setPassword("password");
            user1.setUserId(UUID.randomUUID().toString());
            user1.setProvider(Providers.GOGGLE);
            user1.setEnabled(true);
            
            user1.setEmailVerifed(true);
            user1.setProviderUserId(user.getName());
            user1.setRoleList(List.of(AppConstants.ROLE_USER));
            user1.setAbout("This account is created using Google..");

            user user2=userRepo.findByEmail(email).orElse(null);
            if (user2==null) {
                userRepo.save(user1);
                logger.info("User saved:"+ email);
                
            }*/
            user user2=userRepo.findByEmail(user.getEmail()).orElse(null);
            if (user2==null) {
                userRepo.save(user);
                System.out.println("user saved:"+user.getEmail());
            }

           new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

  }
