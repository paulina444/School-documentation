package com.example.zajecia.controllers;

import com.example.zajecia.entities.StudentCards;
import com.example.zajecia.services.StudentCardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
@RestController
@RequestMapping("/api")
public class StudentCardsController {
    @Autowired
    private StudentCardsService studentCardsService;

    @GetMapping(value = "/legitymacje", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<StudentCards> list(Model model){return studentCardsService.listAllLegitymacje();}

    @PostMapping(value = "/legitymacje")
    public ResponseEntity<StudentCards> create(@RequestBody StudentCards legitymacje){
        studentCardsService.saveLegitymacje(legitymacje);
        return ResponseEntity.ok().body(legitymacje);
    }

    @PutMapping(value = "/legitymacje/{id}")
    public ResponseEntity<Void> edit(@RequestBody StudentCards legitymacje){
        if(!studentCardsService.checkIfExist(legitymacje.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/legitymacje/{id}")
    public RedirectView delete (@PathVariable Integer id){
        studentCardsService.deleteLegitymacje(id);
        return new RedirectView("/api/LegitymacjeList");
    }
}
