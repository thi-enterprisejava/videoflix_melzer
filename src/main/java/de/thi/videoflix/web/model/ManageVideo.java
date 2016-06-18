package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.domain.Video;
import de.thi.videoflix.services.GenreService;
import de.thi.videoflix.util.Events;

import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class ManageVideo implements Serializable {

    @Inject
    private VideoProducer videoProducer;

    @Inject
    GenreService genreService;

    @Inject @Events.Deleted
    private Event<Video> videoDeleteEvent;

    private Video videoToDelete;
    private List<Genre> genres;

    public String doAddVideo() {
        System.out.println("Add new video");
        videoProducer.prepareAddVideo();
        return "addvideo";
    }

    public String doEditVideo(Video video){
        System.out.println("Edit video " +video);
        videoProducer.prepareEditVideo(video);
        return "addvideo";
    }

    public void doDeleteVideo(Video video) {
        this.videoToDelete = video;
        System.out.println("Video is marked to be deleted");
    }

    public String doShowDetails(Video video) {
        System.out.println("Show details for a video: " +video);
        videoProducer.setSelectedVideo(video);
        return "videodetails";
    }

    public void commitDeleteVideo() {
        videoDeleteEvent.fire(videoToDelete);
    }

   public List<Genre> getListGenres() {
        if (genres == null) {
            genres = genreService.getAllGenres();
            genres.sort((g1, g2) -> g1.getName().compareTo(g1.getName()));
        }
        return genres;
    }
}
