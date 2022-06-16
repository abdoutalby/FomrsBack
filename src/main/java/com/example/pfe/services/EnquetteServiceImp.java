package com.example.pfe.services;

import com.example.pfe.Models.Enquette;
import com.example.pfe.Models.Question;
import com.example.pfe.Models.Theme;
import com.example.pfe.Models.User;
import com.example.pfe.controller.EnquetteController;
import com.example.pfe.repositories.EnquetteRepository;
import com.example.pfe.message.NotFoundResponse;
import com.example.pfe.repositories.QuestionRepository;
import com.example.pfe.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class EnquetteServiceImp  implements EnquetteService{

    @Autowired
    EnquetteRepository repository;

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    private ThemeRepository themeRepo;


    @Override
    public Enquette create(Enquette e) {
        return this.repository.save(e);
    }

    @Override
    public List<Enquette> getAll() {
        return this.repository.findAll();
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Enquette> x = this.repository.findById(id);
        if(!x.isPresent()){

            return new ResponseEntity<>(new NotFoundResponse("no enquette found with id : "+id),
                    HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(x , HttpStatus.OK);

   }
    }

    @Override
    public List<Enquette> getByUser(User user) {
        
        
        return this.repository.findByUser(user);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
          this.repository.deleteById(id);
    return new ResponseEntity( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<?> getQuestions(Long id) {
     Optional<Enquette> e = this.repository.findById(id) ;
     if(e.isPresent()){
         List<Question> qs = e.get().getQuestions();
         if(qs.size()>0){

             return new ResponseEntity<>(e.get().getQuestions(),HttpStatus.OK  ) ;
         }else {
             return new ResponseEntity<>(' ',HttpStatus.OK  ) ;
         }
     }else{
         return  new ResponseEntity<>("not found " , HttpStatus.NOT_FOUND);
     }
    }

    @Override
    public ResponseEntity<Boolean> deleteQuestion(Long id, Long idq) {
        Enquette e = this.repository.findById(id).get();
      boolean x =  this.questionRepository.existsById(idq);
        if(x){
            this.questionRepository.delete(this.questionRepository.findById(idq).get());
            return new ResponseEntity<>(  true ,HttpStatus.OK  ) ;
        }else{
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND  ) ;
        }


    }


    public  ResponseEntity<?> addTheme(@PathVariable("eid") Long eid , @RequestBody()Theme theme){
        Optional<Enquette> e = this.repository.findById(eid);
        if(e.isPresent()){
                Enquette up =    e.get();

                    Theme  added = themeRepo.save(theme);
                    up.setThemes(added);
                    this.repository.save(up);


                return new ResponseEntity<>(up , HttpStatus.OK);
        }else{
            return  new ResponseEntity<>("not found ", HttpStatus.NOT_FOUND);
        }
    }
}
