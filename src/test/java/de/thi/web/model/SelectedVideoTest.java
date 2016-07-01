package de.thi.web.model;


import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.domain.Video;
import de.thi.videoflix.services.GenreService;
import de.thi.videoflix.services.VideoService;
import de.thi.videoflix.web.model.Search;
import de.thi.videoflix.web.model.SelectedVideo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

public class SelectedVideoTest {

    SelectedVideo selectedVideo;
    VideoService mockedVideoService;
    GenreService mockedGenreService;

    @Before
    public void setUp() throws Exception {
        mockedVideoService = mock(VideoService.class);
        mockedGenreService = mock(GenreService.class);
        selectedVideo = new SelectedVideo();
    }

    @Test
    public void thatGenresAreListed() throws Exception {
        String fantasy = "Fantasy";
        String drama = "Drama";

        selectedVideo.setGenreService(mockedGenreService);
        List genres = Arrays.asList(new Genre(fantasy), new Genre(drama));
        when(mockedGenreService.getAllGenres()).thenReturn(genres);

        List<Genre> listGenres = selectedVideo.getListGenres();

        assertNotNull(listGenres);
        assertEquals(2, listGenres.size());
        assertEquals(fantasy, listGenres.get(0).getName());
        assertEquals(drama, listGenres.get(1).getName());
    }

   @Test
    public void thatNoVideoIsLoadedWhenVideoIdNotFound() throws Exception {

       selectedVideo.setVideoService(mockedVideoService);

       selectedVideo.setVideoId(1L);
       when(mockedVideoService.findById(1L)).thenReturn(null);
       selectedVideo.init();
       Video video = selectedVideo.getVideo();
       assertNull(video);
    }

    @Test
    public void thatInitLoadsVideoForId() throws Exception {
        String title = "The Godfather";

        Video video = new Video(title);
        video.setId(1L);

        selectedVideo.setVideoService(mockedVideoService);
        selectedVideo.setVideoId(1L);
        when(mockedVideoService.findById(1L)).thenReturn(video);

        selectedVideo.init();

        Video foundVideo = selectedVideo.getVideo();
        assertNotNull(foundVideo);
        assertEquals(video, foundVideo);
        verify(mockedVideoService).findById(1L);
    }


    @Test
    public void thatVideoCanBeAdded() throws Exception {

        String description = "Cool series!";
        String title = "Game of Thrones";
        String genrename = "Fantasy";

        Genre genre = new Genre();
        genre.setName(genrename);
        genre.setId(1L);
        when(mockedGenreService.getAllGenres()).thenReturn(Arrays.asList(genre));

        Part picture = mock(Part.class);
        when(picture.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[]{1, 1, 1}));

        Video video = new Video(title);
        assertEquals(title, video.getName());
        video.setDescription(description);
        assertEquals(description, video.getDescription());

        selectedVideo.setVideoService(mockedVideoService);
        selectedVideo.setGenreService(mockedGenreService);
        selectedVideo.setVideo(video);
        selectedVideo.setImageData(picture);
        selectedVideo.setGenreIds(Arrays.asList("1"));
        
        selectedVideo.doAddVideo();
        ArgumentCaptor<Video> argumentCaptor = ArgumentCaptor.forClass(Video.class);
        verify(mockedVideoService).addVideo(argumentCaptor.capture());
        Video uploadedVideo = argumentCaptor.getValue();
        assertEquals(title, uploadedVideo.getName());
        assertTrue(Arrays.equals(new byte[]{1, 1, 1}, uploadedVideo.getCover()));
        assertEquals(genrename, uploadedVideo.getGenres().get(0).getName());
    }

    @Test
    public void thatVideoCanBeEdited() throws Exception {

        String title = "Game of Thrones";
        String updatedTitle = "Game of Chairs";
        String genrename = "Fantasy";
        String updatedGenrename = "Drama";

        Part picture = mock(Part.class);
        when(picture.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[]{1, 1, 1}));
        Part updatedPicture = mock(Part.class);
        when(updatedPicture.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[]{1, 1, 1, 2}));

        Video video = new Video();
        video.setName(title);

        Genre genre = new Genre();
        genre.setName(genrename);
        genre.setId(1L);

        Genre updatedGenre = new Genre();
        updatedGenre.setName(updatedGenrename);
        updatedGenre.setId(2L);
        when(mockedGenreService.getAllGenres()).thenReturn(Arrays.asList(genre, updatedGenre));

        selectedVideo.setVideoService(mockedVideoService);
        selectedVideo.setGenreService(mockedGenreService);

        selectedVideo.setVideo(video);
        selectedVideo.setImageData(picture);
        selectedVideo.setGenreIds(Arrays.asList("1"));
        ArgumentCaptor<Video> argumentCaptor = ArgumentCaptor.forClass(Video.class);

        selectedVideo.getVideo().setName(updatedTitle);
        selectedVideo.setImageData(updatedPicture);
        selectedVideo.setGenreIds(Arrays.asList("2"));
        selectedVideo.doEditVideo();

        verify(mockedVideoService, times(1)).updateVideo(argumentCaptor.capture());
        Video uploadedVideo = argumentCaptor.getValue();
        assertEquals(updatedTitle, uploadedVideo.getName());
        assertTrue(Arrays.equals(new byte[]{1, 1, 1, 2}, uploadedVideo.getCover()));
        assertEquals(1, uploadedVideo.getGenres().size());
        assertEquals(updatedGenre, uploadedVideo.getGenres().get(0));
    }
}
