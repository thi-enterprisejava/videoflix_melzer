package de.thi.videoflix.rest;

import de.thi.videoflix.domain.Video;
import de.thi.videoflix.services.VideoService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/videos")
public class VideoRestService {

    @EJB
    VideoService videoService;

    @GET
    @Produces("application/json")
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GET
    @Path("/{videoId}")
    @Produces("application/json")
    public Video findById(@PathParam("videoId") Long id) {
        return videoService.findById(id);
    }

    @PUT
    @Consumes("application/json")
    public Response addVideo(Video video) {
        videoService.addVideo(video);
        return Response.accepted().build();
    }

    @POST
    @Consumes("application/json")
    public Response updateVideo(Video video) {
        videoService.updateVideo(video);
        return Response.accepted().build();
    }

    @DELETE
    @Consumes("application/json")
    public Response deleteVideo(Video video) {
        videoService.deleteVideo(video);
        return Response.accepted().build();
    }

}
