package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Video;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class Search implements Serializable {

    private String searchPhrase;
    private List<Video> result;

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public List<Video> getResult() {
        return result;
    }

    public void setResult(List<Video> result) {
        this.result = result;
    }

    public String doSearch() {
        System.out.println("doSearch");

        //Simulate search results
        result = Arrays.asList(
                new Video("Game of Chairs"),
                new Video("Baking Bread")
        );

        return "searchResult";
    }
}
