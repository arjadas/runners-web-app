package com.example.app.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserRestClient {

    private final RestClient restClient;

    /*
    * for customisating the underlying httpclient
    * JdkClientHttpRequestFactory jdkClientHttpRequestFactory = new JdkClientHttpRequestFactory();
    * jdkClientHttpRequestFactory.setConnectTimeout(5000);
    * jdkClientHttpRequestFactory.setReadTimeout(5000);
    * */

    public UserRestClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/")
                //.requestFactory(jdkClientHttpRequestFactory)
                //.defaultHeader("User-Agent", "Spring WebClient")  // setting a default header
                //.requestInterceptor()
                .build();
    }

    public List<User> findAll() {
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public User findById(Integer id) {
        return restClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(User.class); // converting the body to User class
    }

    /* all of these code can be replaced by the userhhtpclient by spring's userhttpclient */
}
