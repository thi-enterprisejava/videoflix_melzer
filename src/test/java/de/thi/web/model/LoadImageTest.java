package de.thi.web.model;

import de.thi.videoflix.domain.Video;
import de.thi.videoflix.services.VideoService;
import de.thi.videoflix.web.model.LoadImage;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class LoadImageTest {

    LoadImage loadImage;
    VideoService mockedVideoService;

    @Before
    public void setUp() throws Exception {
        mockedVideoService = mock(VideoService.class);
        loadImage = new LoadImage();
    }

    @Test
    public void thatCoverCanBeSetAndLoaded() {

        loadImage.setVideoService(mockedVideoService);
        Video video = new Video();
        video.setCover(new byte[]{1, 1, 1});
        doReturn(video).when(mockedVideoService).findById(any());

        byte[] cover = loadImage.getImageForId(1L);

        assertNotNull(cover);
        assertTrue(Arrays.equals(new byte[]{1, 1, 1}, cover));
    }
}
