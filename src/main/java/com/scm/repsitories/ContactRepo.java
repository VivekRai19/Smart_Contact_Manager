package com.scm.repsitories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.entities.Contact;
import com.scm.entities.user;
@Repository
public interface ContactRepo  extends JpaRepository< Contact,String>{
   ///fing the contact by use r
   /// custem finder method
    @Query("SELECT c FROM Contact c WHERE c.user = :user")
   Page<Contact> FindByUser(user user,Pageable pageable);


   //custom query method
   @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
   List<Contact> findByUserId(@Param("userId")String userId);


   Page<Contact> findByUserAndNameContaining(user user,String nameKeyword , Pageable pageable);
   Page<Contact> findByUserAndEmailContaining(user user, String emailKeyword ,Pageable pageable);
   Page<Contact> findByUserAndPhoneNumberContaining( user user,String phoneNumberKeyword ,Pageable pageable);
}


