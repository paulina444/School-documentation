package com.example.zajecia.services;

import com.example.zajecia.entities.StudentCards;

public interface StudentCardsService {
    Iterable<StudentCards> listAllLegitymacje();

    StudentCards saveLegitymacje(StudentCards studentCards);

    Boolean checkIfExist(Integer id);

    void deleteLegitymacje(Integer id);
}
