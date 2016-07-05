package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.services.GenreService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class InitGenres {

    @Inject
    private GenreService genreService;

    @PostConstruct
    public void initGenres(){
        createGenres();
    }

    public void createGenres(){

        System.out.println("Init Genres");
        if (!genreService.getAllGenres().isEmpty()) {
            return;
        }
        genreService.addGenre(new Genre("Abenteuer"));
        genreService.addGenre(new Genre("Action"));
        genreService.addGenre(new Genre("Drama"));
        genreService.addGenre(new Genre("Fantasy"));
        genreService.addGenre(new Genre("Biografie"));
        genreService.addGenre(new Genre("Komödie"));
        genreService.addGenre(new Genre("Horror"));
        genreService.addGenre(new Genre("Krimi"));
        genreService.addGenre(new Genre("Krieg"));
        genreService.addGenre(new Genre("Martial-Arts"));
        genreService.addGenre(new Genre("Science-Fiction"));
        genreService.addGenre(new Genre("Sport"));
        genreService.addGenre(new Genre("Thriller"));
        genreService.addGenre(new Genre("Western"));

    }
}
