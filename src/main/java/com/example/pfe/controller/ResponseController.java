package com.example.pfe.controller;


import com.example.pfe.Models.Response;
import com.example.pfe.services.ResponseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/response")

@CrossOrigin(origins = "http://localhost:4200")
public class ResponseController {

    @Autowired
    ResponseServiceImp responseService;


    @GetMapping
    public List<?> getAll(){
        return this.responseService.getAll();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> repondre(@PathVariable("id") Long id, @RequestBody Response r){
        return  this.responseService.create(id , r);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<?> getByQuestion(@PathVariable("id") Long id){
        return  this.responseService.getByQuestion(id);
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable("id")Long id) {
        String filename = "tutorials.csv";
        InputStreamResource file = new InputStreamResource(responseService.loadCSV(id));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
