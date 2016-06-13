package de.thi.videoflix.services;

import de.thi.videoflix.domain.Video;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class VideoServiceBean implements VideoService {

    @Inject
    EntityManager entityManager;

    @Override
    public List<Video> getAllVideos() {
        TypedQuery<Video> query = entityManager.createQuery("SELECT v FROM Video as v", Video.class);
        return query.getResultList();
    }

    @Override
    public void addVideo(Video video){
        entityManager.persist(video);
    }

    @Override
    public void updateVideo(Video video){
        entityManager.merge(video);
    }

    @Override
    public void deleteVideo(Video video) {
        Video managedVideo = entityManager.find(Video.class, video.getId());
        entityManager.remove(managedVideo);
    }

    @Override
    public List<Video> findByName(String name){
        TypedQuery<Video> query = entityManager.createQuery("SELECT v FROM Video as v WHERE v.name LIKE :name", Video.class);
        query.setParameter("name", name + "%");
        return query.getResultList();
    }
}
