package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Video;
import de.thi.videoflix.services.VideoService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class LoadImage {

    @Inject
    VideoService videoService;


    /**
     * Setter for service for testing purposes
     */
    public void setVideoService(VideoService mockedVideoService){
        this.videoService = mockedVideoService;
    }

    public byte[] getImageForId(Long id) {
        if (id == null) {
            return new byte[0];
        }
        Video vid = videoService.findById(id);
        try {
            return vid.getCover();
        } catch (NullPointerException e) {
            return new byte[0];
        }
    }
}
