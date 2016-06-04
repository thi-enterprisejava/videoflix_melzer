package de.thi.videoflix.web.model;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class EditVideo implements Serializable {

    @Inject
    private VideoListProducer videoListProducer;
    @Inject
    private VideoProducer videoProducer;

    public String doSave(){
        if(videoProducer.isAddMode()){
            videoListProducer.getVideos().add(
                    videoProducer.getSelectedVideo());
        }
        return "listVideos";
    }

    public String doCancel() {
        return "listVideos";
    }
}
