package de.thi.videoflix.domain;

import java.io.Serializable;

/**
 *
 */
public class Genre implements Serializable {

    private String name;

    public Genre(){}
    public Genre(String name){
        this.setName(name);
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
