package com.example.app.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*because rest controller, we expect the response to be in JSON format
* controller should only take a request and delegate a response, not perform any logic or anything else
* if this annotation is removed, our program won't be able to respond to requests
* */

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    /* instead of doing this.runRepository = new runRepository, we included runRepository in the argument because we
    * want to use the already created object of runRepository. we don't want to create a new object of runRepository
    * every time it is called. This is called Dependency Injection.
    * */

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        return runRepository.findById(id);
    }

}
