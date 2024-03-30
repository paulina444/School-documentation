package com.example.zajecia.repositories;

import com.example.zajecia.entities.StudentCards;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentCardsRepository extends CrudRepository<StudentCards, Integer> {
    @Query("select count(*) from StudentCards l where l.id=?1")
    Integer checkIfExist(Integer id);

}
