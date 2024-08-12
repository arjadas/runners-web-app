package com.example.app;

import com.example.app.user.User;
import com.example.app.user.UserHttpClient;
import com.example.app.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*Command Line Runner runs after the application started and the application context has been created*/

	/*@Bean
	CommandLineRunner runner(JdbcClientRunRepository runRepository) {
		return args -> {
			Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR);
			runRepository.create(run);
		};
	}*/

	@Bean
	UserHttpClient userHttpClient() {
		// userhttpclient is using restclient underhood
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}

	@Bean
	CommandLineRunner runner(UserHttpClient client) {  // would be UserRestClient if rest client was used
		return args -> {
			List<User> users = client.findAll();
			System.out.println(users);

			// checking by id
			User user = client.findById(1);
			System.out.println(user);
		};
	}

}
