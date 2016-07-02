package de.thi.service;

import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.domain.Video;
import de.thi.videoflix.services.GenreService;
import de.thi.videoflix.services.GenreServiceBean;
import de.thi.videoflix.services.VideoService;
import de.thi.videoflix.services.VideoServiceBean;
import de.thi.videoflix.util.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ejb.EJB;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(Arquillian.class)
public class VideoServiceIntegrationTest {

    @EJB
    VideoService videoService;

    @EJB
    GenreService genreService;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(VideoService.class)
                .addClass(VideoServiceBean.class)
                .addClass(Video.class)
                .addClass(Genre.class)
                .addClass(GenreService.class)
                .addClass(GenreServiceBean.class)
                .addClass(Resources.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("test-ds.xml")
                ;
        System.out.println(webArchive.toString(Formatters.VERBOSE));
        return webArchive;
    }


    @Test
    @Cleanup
    public void thatAllVideosAreReturned() throws Exception {
        String title1 = "Terminator 2";
        String title2 = "Zwei Asse trumpfen auf";

        Video video1 = new Video();
        video1.setName(title1);

        Video video2 = new Video();
        video2.setName(title2);
        videoService.addVideo(video1);
        videoService.addVideo(video2);

        List<Video> videos = videoService.getAllVideos();

        assertNotNull(videos);
        assertEquals(2, videos.size());
        assertTrue(videos.stream().anyMatch(video -> title1.equals(video.getName())));
        assertTrue(videos.stream().anyMatch(video -> title2.equals(video.getName())));
    }

    @Test
    @Cleanup
    public void thatVideosAreFoundByName() throws Exception {
        String searchPhrase = "Back to the Future";
        String title1 = "Back to the Future 2";
        String title2 = "Back to the Future 3";
        String title3 = "Indiana Jones";
        Video v1 = new Video(title1);
        Video v2 = new Video(title2);
        Video v3 = new Video(title3);
        videoService.addVideo(v1);
        videoService.addVideo(v2);
        videoService.addVideo(v3);

        List<Video> videos = videoService.findByName(searchPhrase);

        assertNotNull(videos);
        assertEquals(2, videos.size());
        assertTrue(videos.stream().anyMatch(video -> title1.equals(video.getName())));
        assertTrue(videos.stream().anyMatch(video -> title2.equals(video.getName())));
    }

    @Test
    @Cleanup
    public void thatVideoCanBeUpdated() throws Exception {

        String title1 = "Back to the Future 2";
        String title2 = "Back to the Future 3";
        Video v1 = new Video();
        videoService.addVideo(v1);

        Video addedVideo = videoService.findById(v1.getId());
        addedVideo.setName(title2);

        videoService.updateVideo(addedVideo);

        Video updatedVideo = videoService.findById(v1.getId());
        assertNotNull(updatedVideo);
        assertEquals(title2, updatedVideo.getName());
    }
}
