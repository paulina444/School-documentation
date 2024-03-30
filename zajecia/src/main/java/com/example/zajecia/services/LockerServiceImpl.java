package com.example.zajecia.services;

import com.example.zajecia.entities.Locker;
import com.example.zajecia.repositories.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LockerServiceImpl implements LockerService {
    @Autowired
    private LockerRepository lockerRepository;

    @Override
    public Locker saveSzafki(Locker szafki) {
        return lockerRepository.save(szafki);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if(lockerRepository.checkIfExist(id)>0)
            return true;
        else
            return false;
    }

    @Override
    public void deleteLocker(Integer id) {
        lockerRepository.deleteById(id);
    }

    @Override
    public Iterable<Locker> listAllLocker() {
        return lockerRepository.findAll();
    }

    @Override
    public List<Locker> findLockerByFloor(String floor) {
        return lockerRepository.findLockerByFloor(floor);
    }


}
