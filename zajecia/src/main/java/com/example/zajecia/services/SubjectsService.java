package com.example.zajecia.services;

import com.example.zajecia.entities.Subjects;

public interface SubjectsService {
    Iterable<Subjects> listAllSubjects();
    Subjects saveSubjects(Subjects subjects);
    Boolean checkIfExist(Integer id);
    void deleteSubjects(Integer id);
}
