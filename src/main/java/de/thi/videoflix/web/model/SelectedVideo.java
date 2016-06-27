package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.domain.Video;
import de.thi.videoflix.services.GenreService;
import de.thi.videoflix.services.VideoService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.omnifaces.util.Utils;

@ViewScoped
@Named
public class SelectedVideo implements Serializable{

    @Inject
    VideoService videoService;

    @Inject
    GenreService genreService;

    private Video video;
    private Long videoId;
    private List<Genre> genres;
    private List<String> genreIds;
    private Part imageData;

    @PostConstruct
    public void postConstruct() {
        System.out.println("New video");
        setVideo(new Video());
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<String> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<String> genreIds) {
        this.genreIds = genreIds;
    }

    public Part getImageData() {
        return imageData;
    }

    public void setImageData(Part imageData) {
        this.imageData = imageData;
    }

    public void init() {
        System.out.println("SelectedVideo init()");

        Video foundVideo = videoService.findById(videoId);
        if(foundVideo != null) {
            System.out.println("Found video: " + foundVideo);
            video = foundVideo;
        } else {
            System.out.println("No video found for id " + videoId);
        }

        this.genreIds = video.getGenres().stream()
                .map(genre -> genre.getId().toString())
                .collect(Collectors.toList());
    }


   public String doAddVideo() throws IOException{
       System.out.println("Add new video" + video);
       updateImage();
       updateGenreForVideo();
       videoService.addVideo(video);
       //return "videodetails.xhtml?faces-redirect=true&video="+video.getId();
       return "listVideos.xhtml";
    }

    public String doEditVideo() throws IOException{
        System.out.println("Edit video " +video);
        updateImage();
        updateGenreForVideo();
        videoService.updateVideo(video);
        //return "videodetails.xhtml?faces-redirect=true&video="+video.getId();
        return "listVideos.xhtml";
    }

    public String doDeleteVideo() {
        System.out.println("Delete Video");
        videoService.deleteVideo(video);
        return "listVideos.xhtml?faces-redirect=true";
    }

    public String doDeleteImage() {
        System.out.println("Delete Image for Video: " +video);
        video.setCover(null);
        videoService.updateVideo(video);
        return "videodetails.xhtml?faces-redirect=true&video="+video.getId();
    }

    public String doShowDetails(Video video) {
        System.out.println("Show details for a video: " +video);
        this.video = video;
        return "videodetails.xhtml?faces-redirect=true&video="+video.getId();
    }

    private void updateGenreForVideo() {
        System.out.println("Setting Genres...");
        List<Genre> genreList = new ArrayList<>(genreIds.size());
        for (String genId : genreIds) {
            long id = Long.parseLong(genId);
            for (Genre g : this.getListGenres()) {
                if (id == g.getId()) {
                    genreList.add(g);
                    break;
                }
            }
            video.setGenres(genreList);
        }
    }

    private void updateImage()throws IOException {
        if (imageData != null) {
            video.setCover(Utils.toByteArray(imageData.getInputStream()));
        }
    }

    public List<Genre> getListGenres() {
        if (genres == null) {
            genres = genreService.getAllGenres();
            genres.sort((g1, g2) -> g1.getName().compareTo(g1.getName()));
        }
        return genres;
    }
}
