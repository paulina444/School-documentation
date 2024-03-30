package com.example.zajecia.services;

import com.example.zajecia.entities.Subjects;
import com.example.zajecia.repositories.StudentsRepository;
import com.example.zajecia.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectsServiceImpl implements SubjectsService{
    @Autowired
    private SubjectsRepository subjectsRepository;
    @Autowired
    private StudentsRepository studentsRepository;

    @Override
    public Iterable<Subjects> listAllSubjects(){
        return subjectsRepository.findAll();
    }

    @Override
    public Subjects saveSubjects(Subjects subjects) {
        return subjectsRepository.save(subjects);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if(subjectsRepository.checkIfExist(id)>0)
            return true;
        else
            return false;
    }
    @Override
    public void deleteSubjects(Integer id){
        subjectsRepository.deleteById(id);
    }
}
