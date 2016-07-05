package de.thi.videoflix.services;


import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.domain.Video;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Alternative
public class MockVideoServiceBean {
    public List<Video> getAllVideos() {

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
                "und Königsmorden aus. Außerdem gibt es Eiszombies und coole Drachen.");
        vid1.setIs_series(true);

        Video vid2 = new Video();
        vid2.setName("Baking Bread");
        vid2.setStudio("Zorny Pictures Television");
        vid2.setDirector("Vince Gilligan");
        vid2.setYear(2008);
        List<Genre> genres2 = new ArrayList<>();
        genres2.add(genre1);
        vid2.setGenres(genres2);
        vid2.setDescription("Der verzweifelte, an Krebs erkrankte Grundschullehrer Walter Weiss muss für seine medizinischen" +
                "Behandlungen Geld auftreiben. Mit einem seiner ehemaligen Schüler, dem 10-jährigen Johannes Pinkmann " +
                "eröffnet er einen illegalen Bäckereibetrieb. Doch Walters Schwager Hans, der beim Gesundheitsamt tätig ist, könnte" +
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
        vid3.setDescription("Die 86-jährige lebenslustige, etwas demente Rentnerin Jessica Day zieht in eine WG in der New Yorker Innenstadt." +
                "Durch ihre charmante vergessliche Art zerstört sie nach und nach das Leben ihrer Mitbewohner.");
        vid3.setIs_series(true);

        List<Video> ret = new ArrayList<>();
        ret.add(vid1);
        ret.add(vid2);
        ret.add(vid3);
        return ret;
    }
}
