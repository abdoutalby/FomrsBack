package com.example.pfe.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank
    private String question;

    @NotBlank
    private String type ;

    @NotBlank
    private boolean status ;


    @ManyToOne
    @JoinColumn(
            name = "enquette_id",
            nullable = false,
            referencedColumnName = "id", // this `id` is the Course.id
            foreignKey = @ForeignKey(name = "enquette_question_fk")
    )
    private Enquette enquette;


    @JsonIgnore
    @OneToMany(mappedBy = "question")
    private Set<Response> responses = new HashSet<>();


    public boolean getStatus() {
        return  this.status;
    }
}
