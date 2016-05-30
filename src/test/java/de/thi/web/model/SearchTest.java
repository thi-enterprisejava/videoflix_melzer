package de.thi.web.model;

import de.thi.videoflix.web.model.Search;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SearchTest {

    Search search;

    @Before
    public void setUp() throws Exception {
        search = new Search();
    }

    @Test
    public void thatSearchNavigatesToListResults() throws Exception {
        String result = search.doSearch();
        assertEquals("searchResult", result);
    }

    @Test
    public void thatResultIsNotEmpty() throws Exception {
        search.doSearch();
        assertNotNull("result should contain videos", search.getResult());
    }

}