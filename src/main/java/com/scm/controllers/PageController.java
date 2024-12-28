package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.user;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class PageController {
@Autowired
    private  UserService userService;
    @GetMapping("/")
    public String index(){
    return "redirect:/home";
    }

    
        
    @RequestMapping("/home")
    
   public String home(Model model){
    System.out.println("Home Page handler");
    // sending data to view
   // model.addAttribute("name", "Substring Technology");
   // model.addAttribute("Youtube", "learn Code with Durgesh");
   // model.addAttribute("goggle", "https://www.youtube.com/watch?v=3p__WB2Kuls&list=PL0zysOflRCemNS641tpw66bcaFylyIGsA&index=3");
    return "home";
   }
//about route
@RequestMapping("/about")
public String aboutPage(Model model){
    model.addAttribute("isLogin", true);
    System.out.println("About the page loding ");
    return "about";
}



//service

@RequestMapping("/services")
public String servicesPage(){

System.out.println("About the page loding ");
    return "services";
}

// contact page
@GetMapping("/contact")
public String contact() {
    return new String("contact");
}
//login page
//this is registration controller
@GetMapping("/login")
public String login() {
    return new String("login");
}
//signup
//do registration or process registration
@GetMapping("/register")
public String register(Model model) {
//defalt data bhi dal skte hai

    UserForm userForm=new UserForm();
   // userForm.setName("Vivek");
   // userForm.setAbout("this is about set your about");
    model.addAttribute("userForm", userForm);
    return new String("register");
}

//processing register page or signup page
@RequestMapping(value = "/do-register", method = RequestMethod.POST)
public String processRegister(@Valid @ModelAttribute UserForm userForm ,BindingResult rBindingResult, HttpSession session){
 System.out.println("Processing Register");
 //fetch from data
 //UserForm
 System.out.println(userForm);
 //validate form data
if(rBindingResult.hasErrors()){
    return "register";
}

 //TODO:VALIDATE THE FORM
 //save to database
 //USER SERVICE
 //userForm--->User
//  user user1=user.builder()
//  .name(userForm.getName())
//  .email(userForm.getEmail())
//  .password(userForm.getPassword())
//  .about(userForm.getAbout())
//  .phoneNumber(userForm.getPhoneNumber())
//  .profilePic("https://www.bing.com/images/search?view=detailV2&ccid=gFFjTsPK&id=15C23A49135A95A26AAF554F2D9BCE8039D7F260&thid=OIP.gFFjTsPKzZ4eLcQTlQ0nIQHaHa&mediaurl=https%3A%2F%2Fcdn-icons-png.flaticon.com%2F512%2F8344%2F8344928.png&exph=512&expw=512&q=default+profile+picture&form=IRPRST&ck=8DE372D9686DEB66570B3D13D4768B1F&selectedindex=2&itb=1&ajaxhist=0&ajaxserp=0&pivotparams=insightsToken%3Dccid_lkVN1WDl*cp_600D173E3EBE84481285EF5DA254B88B*mid_58C757FC3320E51EF037FFD8918ADCE87D692119*simid_608039646319281945*thid_OIP.lkVN1WDlcV2jQCq-9LT7-wHaIJ&vt=0&sim=11&iss=VSI&ajaxhist=0&ajaxserp=0")
//  .build();
user user1=new user();
user1.setName(userForm.getName());
user1.setEmail(userForm.getEmail());
user1.setPassword(userForm.getPassword());
user1.setAbout(userForm.getAbout());
user1.setPhoneNumber(userForm.getPhoneNumber());
user1.setEnabled(false);
user1.setProfilePic("https://www.bing.com/images/search?view=detailV2&ccid=gFFjTsPK&id=15C23A49135A95A26AAF554F2D9BCE8039D7F260&thid=OIP.gFFjTsPKzZ4eLcQTlQ0nIQHaHa&mediaurl=https%3A%2F%2Fcdn-icons-png.flaticon.com%2F512%2F8344%2F8344928.png&exph=512&expw=512&q=default+profile+picture&form=IRPRST&ck=8DE372D9686DEB66570B3D13D4768B1F&selectedindex=2&itb=1&ajaxhist=0&ajaxserp=0&pivotparams=insightsToken%3Dccid_lkVN1WDl*cp_600D173E3EBE84481285EF5DA254B88B*mid_58C757FC3320E51EF037FFD8918ADCE87D692119*simid_608039646319281945*thid_OIP.lkVN1WDlcV2jQCq-9LT7-wHaIJ&vt=0&sim=11&iss=VSI&ajaxhist=0&ajaxserp=0");
  user savedUser=userService.saveUser(user1);
  System.out.println("User SEved................................................");
 //messsage="Registration Successful"
 //add the message


  Message message=Message.builder().content("Registration Successful").type(MessageType.green).build();
 session.setAttribute("message",message);
 //redirect login page

    return "redirect:/register";
}


}


