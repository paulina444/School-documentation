package com.example.zajecia.services;

import com.example.zajecia.entities.StudentCards;
import com.example.zajecia.entities.Students;
import com.example.zajecia.repositories.StudentCardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class StudentCardsServiceImpl implements StudentCardsService {
    @Autowired
    private StudentCardsRepository studentCardsRepository;
    @Override
    public Iterable<StudentCards> listAllLegitymacje() {
        return studentCardsRepository.findAll();
    }

    @Override
    public StudentCards saveLegitymacje(StudentCards legitymacje) {
        return studentCardsRepository.save(legitymacje);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if(studentCardsRepository.checkIfExist(id)>0)
            return true;
        else
            return false;
    }
    public void deleteLegitymacje(Integer id) {
        studentCardsRepository.deleteById(id);
    }



}
