package com.scm.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class user implements UserDetails{

    @Id
    private String userId;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    @Getter(value = AccessLevel.NONE)
    private String password;
    @Column(length = 5000)
    private String about;
    @Column(length = 5000)
    private String profilePic;
    private String phoneNumber;

    //information
    @Getter(value = AccessLevel.NONE)
    private boolean enabled=false;
    private boolean emailVerifed = false;
    private boolean phoneVerifed = false;
    
    // SELF , GOGGLE , FACEBOOK , TWITTER , LINKDIN , GITHUB
    @Enumerated(value = EnumType.STRING)
    private Providers provider=Providers.SELF;
    private String providerUserId;


    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)//cascade is used for deleting if user is deldete all data is deleted
    private List<Contact> contacts= new ArrayList<>();

@ElementCollection(fetch = FetchType.EAGER)
 private List<String> roleList=new ArrayList<>();


 private String emailToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //List of roles[USER ,ADMIN]
        // convert to collection of simplegrantedauthority[roles{ADMIN ,USER}]
       Collection<SimpleGrantedAuthority> roles= roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
       return roles
       ;
    }

// for this project
//email id hai wahi hamare username hai

    @Override
    public String getUsername() {
        return this.email;
    }



    @Override
    public boolean isAccountNonExpired() {
       return true;
    }



    @Override
    public boolean isAccountNonLocked() {
        return true;



    
    
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
       
        return this.enabled;
    }

    @Override
    public String getPassword() {
      return this.password;
    }
    

}
