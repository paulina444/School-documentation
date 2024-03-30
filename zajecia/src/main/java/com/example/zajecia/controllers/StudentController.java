package com.example.zajecia.controllers;

import com.example.zajecia.entities.Students;
import com.example.zajecia.entities.Subjects;
import com.example.zajecia.repositories.StudentsRepository;
import com.example.zajecia.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class StudentController {
    @Autowired
    private StudentsService studentsService;
    @Autowired
    private StudentsRepository uczenRepository;
    private Logger logger = Logger.getLogger("Controller");

    //GET
    @GetMapping(value = "/uczniowie", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Students> list(Model model) {return studentsService.listAllStudents(); }

    //GET
    @GetMapping(value = "/uczniowie/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Students getByPublicId(@PathVariable("id") Integer publicId){
        return studentsService.getStudentsById(publicId).orElse(null);
    }


    // STRONNICOWANIE
    @GetMapping("/students/stronnicowanie")
    public Page<Students> getAllStudents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {
        page=page-1;
        return studentsService.findAllStudents(page, size);
    }

    @GetMapping(value = "/uczniowie/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getByName(@PathVariable("name") String pimie){
        if (studentsService.checkName(pimie)) {
            return ResponseEntity.ok("Imię istnieje w bazie");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imię nie istnieje w bazie");
        }
    }

    //POST zapisywanie do bazy
    @PostMapping(value = "/uczniowie")
    public ResponseEntity<Students> create(@RequestBody @Valid Students uczniowie){
        uczniowie.setIdUcznia(UUID.randomUUID().toString());
        System.out.println(uczniowie.getBirthDate());
        studentsService.saveStudents(uczniowie);
        return ResponseEntity.ok().body(uczniowie);
    }
    @PutMapping(value = "/uczniowie/id")
    public ResponseEntity<Void> edit(@RequestBody Students uczniowie){
        if (!studentsService.checkIfExist(uczniowie.getId()))
        {
            System.out.println("zasób nie został odnaleziony");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            studentsService.saveStudents(uczniowie);
            System.out.println("zamiana danych");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/uczniowie/{id}")
    public String delete(@PathVariable Integer id) {
        studentsService.deleteStudents(id);
        return "User has been deleted success";
    }

    //--------------------------------------------------------------------------//
    //łącząca metoda dwa obiekty (przypisywanie przedmiotu do ucznia)
    @PutMapping("/uczniowie/{idUcznia}/przedmioty/{idPrzedmiotu}")
    public Students assignStudentsSubjects(
            @PathVariable Integer idUcznia,
            @PathVariable int idPrzedmiotu
    ){
        return studentsService.assignStudentsSubjects(idUcznia, idPrzedmiotu);
    }


    //pokazuje przedmioty ucznia
    @GetMapping(value = "/uczniowie/przedmioty/{idUcznia}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Subjects> getPrzedmiotyUcznia(@PathVariable("idUcznia") Integer publicId){
        return studentsService.subjectsOfStudent(publicId);
    }

    //---------------stronnicowanie-----------
    @GetMapping(value = "/uczniowie/strony/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Students> list(@PathVariable("page") Integer pageNr, @RequestParam(value = "size", required = false) Optional<Integer> howManyOnPage){
        return studentsService.listAllStudentsPaging(pageNr, howManyOnPage.orElse(2));
    }

    //---------------OneToOne--------//

    //legitymacje
    @PutMapping("/uczniowie/{idUcznia}/legitymacje/{idLegitymacji}")
    public Students assignUczniowieLegitymacje(
            @PathVariable Integer idUcznia,
            @PathVariable int idLegitymacji
    ){
        return studentsService.assignStudentsStudentCard(idUcznia, idLegitymacji);
    }

    //szafki
    @PutMapping("/uczniowie/{idUcznia}/szafki/{idSzafki}")
    public Students assignUczniowieSzafki(
            @PathVariable Integer idUcznia,
            @PathVariable int idSzafki
    ){
        return studentsService.assignStudentsLocker(idUcznia, idSzafki);
    }

}
