package de.thi.videoflix.services;


import de.thi.videoflix.domain.Video;

import java.util.List;

public interface VideoService {
    List<Video> getAllVideos();
}
