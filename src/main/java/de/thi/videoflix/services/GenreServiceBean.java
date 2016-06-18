package de.thi.videoflix.services;

import de.thi.videoflix.domain.Genre;
import de.thi.videoflix.domain.Video;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
public class GenreServiceBean implements GenreService {

    @Inject
    EntityManager entityManager;

    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre as g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void addGenre(Genre genre){
        entityManager.persist(genre);
    }

    @Override
    public List<Genre> getGenreListForVideo(Long videoId){
        Video managedVideo = entityManager.find(Video.class, videoId);
        List<Genre> genres = managedVideo.getGenres();
        return genres;
    }
}
