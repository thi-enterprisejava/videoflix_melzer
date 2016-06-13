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

    @Inject @Events.Added
    private Event<Video> videoAddEvent;
    @Inject @Events.Updated
    private Event<Video> videoUpdateEvent;
    @Inject
    private VideoProducer videoProducer;

    public String doSave(){
        if(videoProducer.isAddMode()){
            videoAddEvent.fire(videoProducer.getSelectedVideo());
        }else{
            videoUpdateEvent.fire(videoProducer.getSelectedVideo());
        }
        return "listVideos";
    }

    public String doCancel() {
        return "listVideos";
    }
}
