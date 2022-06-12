package com.example.pfe.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
 @Entity
public class Enquette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titre;
    @NotBlank
    private String description ;


    @ManyToOne(cascade = CascadeType.MERGE
            ,targetEntity = User.class,
            fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user" , referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "enquette", // we map the connection field in Subject entity
            orphanRemoval = true

    )
    private List<Question> questions  ;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enquette_themes",
            joinColumns = @JoinColumn(name = "enquette_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id")
    )
    private Set<Theme> themes;


}
