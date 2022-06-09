package com.example.pfe.services;

import com.example.pfe.Models.Reclamation;
import com.example.pfe.Models.User;
import com.example.pfe.repositories.ReclamationRepository;
import com.example.pfe.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamationServiceImp implements ReclamationService {
    @Autowired
    ReclamationRepository repository;

    @Autowired
    UserRepo userRepo;
    @Override
    public Reclamation add(Reclamation r) {
        return repository.save(r);
    }

    @Override
    public List<Reclamation> getall() {
        return repository.findAll();
    }


    public ResponseEntity<?> getByUser(Long user) {
        Optional<User> u = userRepo.findById(user);
        if(u.isPresent()){
            List<Reclamation> reclamations = repository.findAllByUser(u.get());
            return  new ResponseEntity<>( reclamations,HttpStatus.OK );
        }else {
            return  new ResponseEntity<>("not found ",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Reclamation getById(Long id) {
        return repository.findById(id).get();
    }
}
