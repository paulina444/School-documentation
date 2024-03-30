package com.example.zajecia.controllers;

import com.example.zajecia.entities.StudentCards;
import com.example.zajecia.entities.Students;
import com.example.zajecia.entities.Subjects;
import com.example.zajecia.services.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class SubjectController {
    @Autowired
    private SubjectsService subjectsService;

    @GetMapping(value = "/przedmioty", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Subjects> list(Model model){ //Klasa Model jest obiektem, który można użyć do przekazywania danych do widoku lol
        return subjectsService.listAllSubjects();
    }

    @PostMapping(value = "/przedmioty")
    public ResponseEntity<Subjects> create(@RequestBody Subjects subjects) {
        subjectsService.saveSubjects(subjects);
        return ResponseEntity.ok().body(subjects);
    }

    @PutMapping(value = "/przedmioty/id")
    public ResponseEntity<Void> edit(@RequestBody Subjects subjects){
        if(!subjectsService.checkIfExist(subjects.getId())){
            System.out.println("zasób nie został odnaleziony");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            System.out.println("zamiana danych");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @DeleteMapping(value = "/przedmioty/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        subjectsService.deleteSubjects(id);
        return new RedirectView("/api/przedmiotyList", true);
    }
}
