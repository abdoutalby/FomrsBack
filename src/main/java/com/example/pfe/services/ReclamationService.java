package com.example.pfe.services;

import com.example.pfe.Models.Reclamation;
import com.example.pfe.Models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReclamationService {
    public Reclamation add(Reclamation r);
    public List<Reclamation> getall();
    public ResponseEntity<?> getByUser(Long user);
    public Reclamation getById(Long id);
}
