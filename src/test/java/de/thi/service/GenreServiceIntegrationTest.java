package de.thi.service;


import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.services.GenreService;
import de.thi.videoflix.services.GenreServiceBean;
import de.thi.videoflix.util.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class GenreServiceIntegrationTest {

    @EJB
    GenreService genreService;

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(GenreService.class)
                .addClass(GenreServiceBean.class)
                .addClass(Genre.class)
                .addClass(Resources.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("test-ds.xml")
                ;
        return webArchive;
    }

    @Test
    @Cleanup
    public void thatGenresCanBeAddedAndFound() throws Exception {
        String name1 = "Action";
        String name2 = "Fantasy";

        Genre genre1 = new Genre(name1);
        Genre genre2 = new Genre(name2);;
        genreService.addGenre(genre1);
        genreService.addGenre(genre2);

        List<Genre> genres = genreService.getAllGenres();

        assertNotNull(genres);
        assertEquals(2, genres.size());
        assertTrue(genres.stream().anyMatch(genre -> name1.equals(genre.getName())));
        assertTrue(genres.stream().anyMatch(genre -> name2.equals(genre.getName())));
    }
}
