package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Video;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
public class VideoProducer implements Serializable {

    private enum Mode {
        EDIT, ADD
    }

    private Video video;
    private Mode mode;

    @Produces
    @Named
    public Video getSelectedVideo() {
        return video;
    }

    public void setSelectedVideo(Video video) {
        this.video = video;
    }

    @Produces
    @Named
    public Boolean isAddMode() {
        return mode == Mode.ADD;
    }

    public void prepareAddVideo() {
        this.video = new Video();
        this.mode = Mode.ADD;
    }

    public void prepareEditVideo(Video video) {
        this.video = video;
        this.mode = Mode.EDIT;
    }
}
