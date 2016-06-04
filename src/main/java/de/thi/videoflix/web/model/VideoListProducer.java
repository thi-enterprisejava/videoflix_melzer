package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Video;
import de.thi.videoflix.domain.Genre;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

@SessionScoped
@Named
public class VideoListProducer implements Serializable{

    private List<Video> videos;

    public VideoListProducer() {
        videos = createMockVideos();
    }

    public List<Video> getVideos(){
        return videos;
    }

    public List<Video> createMockVideos() {
        Video vid1 = new Video();
        vid1.setName("Game of Chairs");
        vid1.setStudio("HBO");
        vid1.setDirector("David Benioff");
        vid1.setYear(2011);
        Genre genre1 = new Genre();
        genre1.setName("Drama");
        Genre genre2 = new Genre();
        genre2.setName("Fantasy");
        List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);
        vid1.setGenres(genres);
        vid1.setDescription("Eine harmlose Partie \"Reise nach Jerusalem\" artet in ein wirres Netz aus Intrigen, Gewalt, " +
                "und K�nigsmorden aus. Au�erdem gibt es Eiszombies und coole Drachen.");
        vid1.setIs_series(true);

        Video vid2 = new Video();
        vid2.setName("Baking Bread");
        vid2.setStudio("Zorny Pictures Television");
        vid2.setDirector("Vince Gilligan");
        vid2.setYear(2008);
        List<Genre> genres2 = new ArrayList<>();
        genres2.add(genre1);
        vid2.setGenres(genres2);
        vid2.setDescription("Der verzweifelte, an Krebs erkrankte Grundschullehrer Walter Weiss muss f�r seine medizinischen" +
                "Behandlungen Geld auftreiben. Mit einem seiner ehemaligen Sch�ler, dem 10-j�hrigen Johannes Pinkmann " +
                "er�ffnet er einen illegalen B�ckereibetrieb. Doch Walters Schwager Hans, der beim Gesundheitsamt t�tig ist, k�nnte" +
                "ihnen auf die Schliche kommen.");
        vid2.setIs_series(true);

        Video vid3 = new Video();
        vid3.setName("Old Girl");
        vid3.setStudio("19th Century Fox Television");
        vid3.setDirector("Elizabeth Meriwether");
        vid3.setYear(2011);
        Genre genre3 = new Genre();
        genre3.setName("Comedy");
        Genre genre4 = new Genre();
        genre4.setName("Sitcom");
        List<Genre> genres3 = new ArrayList<>();
        genres3.add(genre3);
        genres3.add(genre4);
        vid3.setGenres(genres3);
        vid3.setDescription("Die 86-j�hrige lebenslustige, etwas demente Rentnerin Jessica Day zieht in eine WG in der New Yorker Innenstadt." +
                "Durch ihre charmante vergessliche Art zerst�rt sie nach und nach das Leben ihrer Mitbewohner.");
        vid3.setIs_series(true);

        List<Video> ret = new ArrayList<>();
        ret.add(vid1);
        ret.add(vid2);
        ret.add(vid3);
        return ret;

    }
}
