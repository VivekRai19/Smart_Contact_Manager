package com.scm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {
    @Value("${cloudinary.cloud_name}")
    private String cloud_Name;
      
    @Value("${cloudinary.api_key}")
    private String api_Key;

    @Value("${cludinary.api_secret}")
    private String api_Secret;
















    @Bean
    public Cloudinary cloudinary(){




        return new Cloudinary(
    
        ObjectUtils.asMap("cloud_name",
        cloud_Name,"api_key",api_Key,
        "api_secret",api_Secret)





        );
    }

}
