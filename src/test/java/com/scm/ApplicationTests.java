package com.scm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.services.EmailService;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}
    @Autowired
    private EmailService service;






    @Test
	void sendEmailTest(){
		service.sendEmail("vivekrai8086@gmail.com", "HAPPY BIRTHDAY KRITI TIWARI JI", "Many happy returns of the day. Hope you feel nothing but loved and appreciated throughout the day and year. May God bless you and guide you to fulfil all our desires. Happy birthday. You are the reason I smile, my source of love and happiness. Happy Birthday, my girl ❤️");

	}

}
