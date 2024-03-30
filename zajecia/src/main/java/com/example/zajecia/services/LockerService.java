package com.example.zajecia.services;

import com.example.zajecia.entities.Locker;

import java.util.List;

public interface LockerService {
    Locker saveSzafki(Locker locker);
    Boolean checkIfExist(Integer id);

    void deleteLocker(Integer id);

    public Iterable<Locker> listAllLocker();

    List<Locker> findLockerByFloor(String floor);
}
