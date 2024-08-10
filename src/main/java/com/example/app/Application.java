package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {


		/* method 1

		SpringApplication.run(Application.class, args);
		WelcomeMessage welcomeMessage = new WelcomeMessage();
		System.out.println(welcomeMessage.getWelcomeMessage());

		 */

		// method 2 - Spring's dependency injection

		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		WelcomeMessage welcomeMessage2 = (WelcomeMessage) context.getBean("welcomeMessage");
		System.out.println(welcomeMessage2);

	}

}
