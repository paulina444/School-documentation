package com.example.zajecia.repositories;

import com.example.zajecia.entities.Subjects;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectsRepository extends CrudRepository<Subjects, Integer> {
    List<Subjects> findByName(String name);
    Integer countUczniowieById(Integer id);

    @Query("select count(*) from Subjects p where p.id = ?1")
    Integer checkIfExist(Integer id);
}
