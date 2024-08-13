package com.example.app.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemoryRunRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryRunRepository.class);
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    /* Optional lets a method return a null value */

    public Optional<Run> findById(Integer id) {
        return Optional.ofNullable(runs.stream()
                .filter(run -> run.id().equals(id))
                .findFirst()
                .orElseThrow(RunNotFoundException::new));
    }

    public void create(Run run) {
        runs.add(run);
    }

    public void update(Run newRun, Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            var run = existingRun.get();
            log.info("Updating Existing Run: " + existingRun.get());
            runs.set(runs.indexOf(run), newRun);
        }
    }

    public void delete(Integer id) {
        runs.removeIf(run -> run.id().equals(id));
    }

    public int count() {
        return runs.size();
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(run -> create(run));
    }

    public List<Run> findByLocation(String location) {
        return runs.stream()
                .filter(run -> Objects.equals(run.location(), location))
                .toList();
    }

    /*post construct does some initialisations after the dependency injection is done*/

    @PostConstruct
    private void init() {
        runs.add(new Run(1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                Location.INDOOR,
                null));

        runs.add(new Run(2,
                "Wednesday Evening Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                6,
                Location.INDOOR,
                null));

    }

}