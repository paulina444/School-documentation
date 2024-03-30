package com.example.zajecia.repositories;

import com.example.zajecia.entities.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface StudentsRepository  extends CrudRepository<Students, Integer>, PagingAndSortingRepository<Students, Integer> {
    @Query("select count(*) from Students u where u.id = ?1")
    Integer checkIfExist(Integer id);

    @Query("select count(*) from Students u where u.name=?1")
    Integer checkName(String name);
    @Query("select s FROM Students s ORDER BY s.id")
    Page<Students> findStudents(Pageable pageable);

}