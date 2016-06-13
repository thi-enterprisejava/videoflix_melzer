package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Video;
import de.thi.videoflix.services.VideoService;
import de.thi.videoflix.util.Events;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class VideoListProducer {

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
        videoService.addVideo(video);
        init();
    }

    public void onVideoDeleted(@Observes @Events.Deleted Video video) {
        videoService.deleteVideo(video);
        init();
    }

    public void  onVideoUpdated(@Observes @Events.Updated Video video){
        videoService.updateVideo(video);
        init();
    }
}
