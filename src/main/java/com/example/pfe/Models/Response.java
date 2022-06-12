package com.example.pfe.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE ,CascadeType.REMOVE}
            ,targetEntity = Question.class,
            fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question" , referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private  Question question;

    private  String reponse ;

    @ManyToOne(cascade = CascadeType.MERGE
            ,targetEntity = User.class,
            fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user" , referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user ;
}
