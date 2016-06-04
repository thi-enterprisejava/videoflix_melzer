package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Video;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class ManageVideo implements Serializable {

    public String doAddVideo() {
        System.out.println("Add new video");
        return "addvideo";
    }

    public String doEditVideo(Video video){
        System.out.println("Edit video " +video);
        return "addvideo";
    }

    public String doListVideos(){
        System.out.println("List all videos");
        return "listVideos";
    }

    public void doDeleteVideo(Video video) {
        System.out.println("Deletion not implemented yet");
    }

    public String doShowDetails(Video video) {
        System.out.println("Show details for a video");
        return "videodetails";
    }

    public void commitDeleteGame() {
        System.out.println("Not implemented yet");
    }
}
