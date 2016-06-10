package de.thi.videoflix.web.model;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
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
