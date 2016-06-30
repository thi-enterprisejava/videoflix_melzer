package de.thi.web.model;

import de.thi.videoflix.domain.Video;
import de.thi.videoflix.services.VideoService;
import de.thi.videoflix.web.model.Search;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SearchTest {

    Search search;
    private VideoService mockedVideoService;

    @Before
    public void setUp() throws Exception {
        mockedVideoService = Mockito.mock(VideoService.class);
        search = new Search(mockedVideoService);
    }

    @Test
    public void thatSearchNavigatesToSearchResults() throws Exception {
        String result = search.doSearch();
        assertEquals("searchResult", result);
    }

    @Test
    public void thatResultIsNotEmpty() throws Exception {
        search.doSearch();
        assertNotNull("result should contain videos", search.getResult());
    }

    @Test
    public void thatResultsContainsAddedVideo() throws Exception {
        Video video = new Video("Test");
        when(mockedVideoService.findByName(any()))
                .thenReturn(Arrays.asList(video));
        search.setSearchPhrase("Test1234");
        search.doSearch();
        assertEquals(1, search.getResult().size());
        assertEquals(video, search.getResult().get(0));
    }

}