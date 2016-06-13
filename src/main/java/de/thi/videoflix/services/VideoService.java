package de.thi.videoflix.services;


import de.thi.videoflix.domain.Video;

import java.util.List;

public interface VideoService {
    List<Video> getAllVideos();

    void addVideo(Video video);

    void deleteVideo(Video video);

    void updateVideo(Video video);

    List<Video> findByName(String name);
}
