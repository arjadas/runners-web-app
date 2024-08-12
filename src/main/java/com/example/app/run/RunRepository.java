package com.example.app.run;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;  // from spring data

import java.util.List;

public interface RunRepository extends ListCrudRepository<Run, Integer> {

    @Query
    List<Run> findByLocation(String location);

    // we could've added custom query here but spring data already has it for us
    // @Query("SELECT * FROM run WHERE location = :location")
}

/*
* If we didn't extend from ListCrudRepository, we would have to write all the CRUD operations manually
*
public interface RunRepository {

    List<Run> findAll();

    Optional<Run> findById(Integer id);

    void create(Run run);

    void update(Run run, Integer id);

    void delete(Integer id);

    int count();

    void saveAll(List<Run> runs);

    List<Run> findByLocation(String location);

}
*
* */