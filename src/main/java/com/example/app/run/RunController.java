package com.example.app.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/*because rest controller, we expect the response to be in JSON format
* controller should only take a request and delegate a response, not perform any logic or anything else
* if this annotation is removed, our program won't be able to respond to requests
* the getmapping, postmapping, putmapping and deletemapping are the endpoints
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

    // get (read)

    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);
        if (run.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return run.get();
    }

    // post (create)
    // the run here would be a request Body from REST API in JSON format, so we let spring know about it by annotation

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Run run) {
        runRepository.create(run);
    }

    // put (edit)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void create(@RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(run, id);
    }

    // delete

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }


}
