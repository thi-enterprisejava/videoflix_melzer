package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Video;
import de.thi.videoflix.services.VideoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class Search implements Serializable {

    @Inject
    VideoListProducer videoListProducer;

    @Inject
    VideoService videoService;

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
        result = videoService.findByName(searchPhrase);
        return "searchResult";
    }
}
