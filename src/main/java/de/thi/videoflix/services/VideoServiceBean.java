package de.thi.videoflix.services;

import de.thi.videoflix.domain.Video;

import javax.enterprise.context.RequestScoped;
import java.util.LinkedList;
import java.util.List;

@RequestScoped
public class VideoServiceBean implements VideoService {
    @Override
    public List<Video> getAllVideos() {
        return new LinkedList<Video>();
    }
}
