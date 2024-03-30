package com.example.zajecia.services;

import com.example.zajecia.entities.Students;
import com.example.zajecia.entities.Subjects;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

public interface StudentsService {
    Iterable<Students> listAllStudents();
    Optional<Students> getStudentsById(Integer id);
    Students saveStudents(Students uczniowie);
    void deleteStudents(Integer id);
    Boolean checkIfExist(Integer id);
    Boolean checkName(String name);
    Iterable<Students> listAllStudentsPaging(Integer pageNr, Integer howManyOnPage);

    Students assignStudentsSubjects(Integer idUcznia, int idPrzedmiotu);

    Set<Subjects> subjectsOfStudent(Integer idUcznia);

    Students assignStudentsLocker(Integer idUcznia, int idSzafki);

    Students assignStudentsStudentCard(Integer idUcznia, int idLegitymacji);

    Page<Students> findAllStudents(int page, int size);

}
