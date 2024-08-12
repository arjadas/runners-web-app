package com.example.app.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    /* Instead of doing (this.runRepository = new runRepository), we included runRepository in the argument because we
    * want to use the already created object of runRepository. We don't want to create a new object of runRepository
    * every time it is called. This is called Dependency Injection.
    * All of the CRUD operations are happening in-memory when not connected to database. So, if the server is closed,
    * any new added runs will be lost.
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
            throw new RunNotFoundException();
        }
        return run.get();
    }

    @GetMapping("/location/{location}")
    List<Run> findAllByLocation(@PathVariable String location) {
        return runRepository.findByLocation(location);
    }

    // post (create)
    // the run here would be a request Body from REST API in JSON format, so we let spring know about it by annotation

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        runRepository.save(run);  // spring data jdbc will automatically insert the row
        // runRepository.create(run);  // if we were using jdbcClient
    }

    // put (edit)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.save(run);  // spring data jdbc will automatically update the row
        // runRepository.update(run, id);  // if we were using jdbcClient
    }

    // delete

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(runRepository.findById(id).get());  // spring data jdbc deletes the row by run object
        // runRepository.delete(id);  // if we were using jdbcClient
    }


}
