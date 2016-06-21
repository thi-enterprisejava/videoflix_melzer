package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.domain.Video;
import de.thi.videoflix.services.GenreService;
import de.thi.videoflix.services.VideoService;
import de.thi.videoflix.util.Events;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ViewScoped
@Named
public class SelectedVideo implements Serializable{

    @Inject
    VideoService videoService;

    @Inject
    GenreService genreService;

    @Inject @Events.Changed
    private Event<Video> videoChangeEvent;

    private Video video;
    private Long videoId;
    private List<Genre> genres;
    private List<String> genreIds;

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


   public String doAddVideo() {
       System.out.println("Add new video" + video);
       updateGenreForVideo();
       videoService.addVideo(video);
       //videoChangeEvent.fire(video);
       //return "videodetails.xhtml?faces-redirect=true&video="+video.getId();
       return "listVideos.xhtml";
    }

    public String doEditVideo(){
        System.out.println("Edit video " +video);
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

    public String doDeleteVideo2(Video video) {
        this.video = video;
        videoService.deleteVideo(video);
        return "listVideos.xhtml?faces-redirect=true";
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

    public List<Genre> getListGenres() {
        if (genres == null) {
            genres = genreService.getAllGenres();
            genres.sort((g1, g2) -> g1.getName().compareTo(g1.getName()));
        }
        return genres;
    }

}