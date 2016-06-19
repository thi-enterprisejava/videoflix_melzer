package de.thi.videoflix.services;


import de.thi.videoflix.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAllGenres();

    void addGenre(Genre genre);

   // List<Genre> getGenreListForVideo(Long videoId);
}
