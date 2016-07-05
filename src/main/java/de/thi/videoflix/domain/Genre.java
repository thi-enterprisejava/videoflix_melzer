package de.thi.videoflix.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Genre implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String name;

    public Genre(){}

    public Genre(String name){
        this.setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    Abenteuer
    Action
    Drama
    Fantasy
    Biografie
    Komödie
    Horror
    Krimi
    Krieg
    Martial-Arts
    Science-Fiction
    Sport
    Thriller
    Western
     */
}
