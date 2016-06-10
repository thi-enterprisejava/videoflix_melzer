package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Video;
import de.thi.videoflix.util.Events;

import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class EditVideo implements Serializable {

    @Inject
    private VideoProducer videoProducer;
    @Inject @Events.Added
    private Event<Video> videoAddEvent;

    public String doSave(){
        if(videoProducer.isAddMode()){
            videoAddEvent.fire(videoProducer.getSelectedVideo());
        }
        return "listVideos";
    }

    public String doCancel() {
        return "listVideos";
    }
}
