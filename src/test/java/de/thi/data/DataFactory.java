package de.thi.data;


import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.domain.Video;

import java.util.ArrayList;
import java.util.List;

public class DataFactory {

    public static Video createTestVideo(){
        Video video = new Video();
        video.setName("Game of Chairs");
        video.setStudio("HBO");
        video.setDirector("David Benioff");
        video.setYear(2011);
        Genre genre1 = new Genre();
        genre1.setName("Drama");
        Genre genre2 = new Genre();
        genre2.setName("Fantasy");
        List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);
        video.setGenres(genres);
        video.setDescription("Eine harmlose Partie \"Reise nach Jerusalem\" artet in ein wirres Netz aus Intrigen, Gewalt, " +
                "und Königsmorden aus. Außerdem gibt es Eiszombies und coole Drachen.");
        video.setIs_series(true);
        return video;
    }
}
