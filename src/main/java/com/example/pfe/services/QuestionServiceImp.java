package com.example.pfe.services;

import com.example.pfe.Models.Enquette;
import com.example.pfe.Models.Question;
import com.example.pfe.repositories.EnquetteRepository;
import com.example.pfe.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImp  implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    EnquetteRepository enquetteRepository;
    @Override
    public ResponseEntity<?> create(Long id, Question q) {
      Optional<Enquette> e =  this.enquetteRepository.findById(id) ;
      if(e.isPresent()){
          q.setEnquette(e.get());
          Question qes = this.questionRepository.save(q);
          return new ResponseEntity<>(qes,HttpStatus.OK) ;
      }
      return new ResponseEntity<>("enquette not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Question> getByEnquette(Enquette e) {
        return this.questionRepository.findByEnquette(e);
    }
}
