package com.example.pfe.controller;

import com.example.pfe.Models.Reclamation;
import com.example.pfe.services.ReclamationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reclamation/")
public class ReclamationController {

    @Autowired
    ReclamationServiceImp reclamationServiceImp;
    @PostMapping()
    public Reclamation save(@RequestBody Reclamation r){
            return this.reclamationServiceImp.add(r);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<?> getAllByUser(@PathVariable("user") Long uid){
            return this.reclamationServiceImp.getByUser(uid);
    }

    @GetMapping()
    public List<Reclamation> getAll(){
        return this.reclamationServiceImp.getall();
    }

    @GetMapping("/{id}")
    public  Reclamation getById(@PathVariable("id") Long id){
        return this.reclamationServiceImp.getById(id);
    }
}
