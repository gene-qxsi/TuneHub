package com.matrosov.service;

import com.matrosov.entity.Song;

import java.util.List;
import java.util.Optional;

public interface Service<K, V> {

    public List<V> findAll();

    public Optional<V> findById(K id);

    public boolean delete(K id);


    public void update(V entity);

    public V save(V entity);

    public boolean deleteAll();

}
