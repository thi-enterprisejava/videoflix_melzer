package de.thi.videoflix.rest;


import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.services.GenreService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/genres")
public class GenreRestService {

    @EJB
    GenreService genreService;

    @GET
    @Produces("application/json")
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PUT
    @Consumes("application/json")
    public Response addGenre(Genre genre) {
        genreService.addGenre(genre);
        return Response.accepted().build();
    }

}