package com.example.app.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*because rest controller, we expect the response to be in JSON format
* controller should only take a request and delegate a response, not perform any logic or anything else
* if this annotation is removed, our program won't be able to respond to requests
*/

@RestController
public class RunController {

    @GetMapping("/hello")
    String home() {
        return "Hello Runners!";
    }




}
