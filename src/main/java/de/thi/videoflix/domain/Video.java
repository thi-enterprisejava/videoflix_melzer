package de.thi.videoflix.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Video implements Serializable {

    @GeneratedValue
    @Id
    private long id;

    private String name;
    private String studio;
    private String director;
    private int year;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Genre> genres = new ArrayList<>();
    private String description;
    private Boolean is_series;
    @Lob
    private byte[] cover;

    public Video(){}

    public Video(String name){
        this.setName(name);
    }

    public long getId() {
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

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_series() {
        return is_series;
    }

    public void setIs_series(Boolean is_series) {
        this.is_series = is_series;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }
}
