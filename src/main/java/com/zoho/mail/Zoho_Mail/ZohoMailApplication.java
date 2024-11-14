package com.zoho.mail.Zoho_Mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZohoMailApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");

		SpringApplication.run(ZohoMailApplication.class, args);
	}


}
