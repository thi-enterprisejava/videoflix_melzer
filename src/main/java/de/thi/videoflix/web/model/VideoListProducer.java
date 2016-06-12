package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Video;
import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.services.VideoService;
import de.thi.videoflix.util.Events;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

@SessionScoped
public class VideoListProducer implements Serializable{

    private List<Video> videos;

    @Inject
    private VideoService videoService;

    @PostConstruct
    public void init() {
        videos = videoService.getAllVideos();
    }

    @Produces
    @Named
    public List<Video> getVideos(){
        return videos;
    }

    public void onVideoAdded(@Observes @Events.Added Video video){
        getVideos().add(video);
    }

    public void onVideoDeleted(@Observes @Events.Deleted Video video) {
        getVideos().remove(video);
    }
}
