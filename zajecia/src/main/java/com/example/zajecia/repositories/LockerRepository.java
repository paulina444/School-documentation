package com.example.zajecia.repositories;

import com.example.zajecia.entities.Locker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LockerRepository extends CrudRepository<Locker,Integer> {
    @Query("select count(*) from Locker s where s.id=?1")
    Integer checkIfExist(Integer id);

    @Query("SELECT s FROM Locker s WHERE s.floor = :floor")
    List<Locker> findLockerByFloor(@Param("floor") String floor);

}
