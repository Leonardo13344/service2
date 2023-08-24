package com.distribuida.repo;


import com.distribuida.db.Album;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class AlbumRepo {

    @PersistenceContext(unitName = "servicio2")
    private EntityManager em;

    public List<Album> findAll() {
        return em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
    }

    public Album findById(Integer id) {
        return em.find(Album.class, id);
    }

    public Album create(Album album) {
        em.persist(album);
        return album;
    }

    public Album update(Album album) {
        return em.merge(album);
    }

    public void delete(Integer id) {
        Album album = em.find(Album.class, id);
        em.remove(album);
    }

}
