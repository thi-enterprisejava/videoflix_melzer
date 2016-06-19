package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Video;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class EditVideo implements Serializable {

    public String doSwitchToEditPage(Video video){
        System.out.println("Switch to Edit Page: "+video.getId());
        return "editvideo.xhtml?faces-redirect=true&video="+video.getId();
    }

    public String doCancel() {
        System.out.println("doCancel");
        return "listVideos.xhtml";
    }
}
