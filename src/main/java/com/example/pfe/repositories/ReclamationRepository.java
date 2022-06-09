package com.example.pfe.repositories;

import com.example.pfe.Models.Reclamation;
import com.example.pfe.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long > {
    List<Reclamation> findAllByUser(User user);
}
