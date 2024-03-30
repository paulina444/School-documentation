package com.example.zajecia.controllers;

import com.example.zajecia.entities.Locker;
import com.example.zajecia.services.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LockerController {
    @Autowired
    private LockerService lockerService;

    @GetMapping(value = "/szafki", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Locker> list(Model model){return lockerService.listAllLocker();}

    @PostMapping(value ="/szafki")
    public ResponseEntity<Locker> create(@RequestBody Locker szafki){
        lockerService.saveSzafki(szafki);
        return ResponseEntity.ok().body(szafki);
    }

    @PutMapping(value = "/szafki/id")
    public ResponseEntity<Void> edit(@RequestBody Locker locker) {
        if (!lockerService.checkIfExist(locker.getId())) {
            System.out.println("zasób nie został odnaleziony");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            System.out.println("zamiana danych");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/szafki/{id}")
    public RedirectView delete (@PathVariable Integer id){
        lockerService.deleteLocker(id);
        return new RedirectView("/api/szafkiList");
    }
    @GetMapping("/szafki/pietro")
    public List<Locker> getLockersByFloor(@RequestParam String floor) {
        return lockerService.findLockerByFloor(floor);
    }

}
