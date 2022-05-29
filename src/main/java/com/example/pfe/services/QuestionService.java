package com.example.pfe.services;

import com.example.pfe.Models.Enquette;
import com.example.pfe.Models.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

    public ResponseEntity<?> create(Long id, Question q);

    public List<Question> getByEnquette(Enquette e);
}
