package com.example.zajecia.services;

import com.example.zajecia.entities.Locker;
import com.example.zajecia.entities.StudentCards;
import com.example.zajecia.entities.Students;
import com.example.zajecia.entities.Subjects;
import com.example.zajecia.repositories.LockerRepository;
import com.example.zajecia.repositories.StudentCardsRepository;
import com.example.zajecia.repositories.StudentsRepository;
import com.example.zajecia.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentsServiceImpl implements StudentsService{
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private SubjectsRepository subjectsRepository;
    @Autowired
    private StudentCardsRepository studentCardsRepository;
    @Autowired
    private LockerRepository lockerRepository;

    @Override
    public Iterable<Students> listAllStudents(){
        return studentsRepository.findAll();
    }

    @Override
    public Optional<Students> getStudentsById(Integer id) { return studentsRepository.findById(id); }

    @Override
    public Students saveStudents(Students students) {return studentsRepository.save(students); }

    @Override
    public void deleteStudents(Integer id) {studentsRepository.deleteById(id); }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (studentsRepository.checkIfExist(id)>0)
            return true;
        else
            return false;
    }

    @Override
    public Boolean checkName(String name){
        if (studentsRepository.checkName(name) > 0)
            return true;
        else
            return false;
    }

    @Override
    public Iterable<Students> listAllStudentsPaging(Integer pageNr, Integer howManyOnPage){
        return studentsRepository.findAll(PageRequest.of(pageNr,howManyOnPage));
    }

    @Override
    public Students assignStudentsSubjects(Integer studentsId, int subjectsId) {
        Set<Subjects> subjectsSet = null;
        Students students = studentsRepository.findById(studentsId).get();
        Subjects subjects = subjectsRepository.findById(subjectsId).get();
        subjectsSet = students.getAssignedSubjects();
        subjectsSet.add(subjects);
        students.setAssignedSubjects(subjectsSet);
        return studentsRepository.save(students);
    }

    @Override
    public Set<Subjects> subjectsOfStudent(Integer idUcznia) {
        Students student = studentsRepository.findById(idUcznia).orElse(null);

        if (student != null) {
            return student.getAssignedSubjects();
        } else {
            throw new EntityNotFoundException("Uczeń o podanym ID nie został znaleziony");
        }
    }

    @Override
    public Students assignStudentsStudentCard(Integer idUcznia, int idLegitymacji) {
        Students uczniowie = studentsRepository.findById(idUcznia).get();
        StudentCards studentCards = studentCardsRepository.findById(idLegitymacji).get();
        uczniowie.setAssignedStudentCards(studentCards);
        return studentsRepository.save(uczniowie);
    }

    @Override
    public Students assignStudentsLocker(Integer idUcznia, int idSzafki) {
        Students uczniowie = studentsRepository.findById(idUcznia).get();
        Locker szafki = lockerRepository.findById(idSzafki).get();
        uczniowie.setAssignedLocker(szafki);
        return studentsRepository.save(uczniowie);
    }

    @Override
    public Page<Students> findAllStudents(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return studentsRepository.findStudents(pageable);
    }
}
