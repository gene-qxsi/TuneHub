package com.matrosov.service;

import com.matrosov.dao.SongDao;
import com.matrosov.entity.Song;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SongService implements Service<Integer, Song>{

    private final SongDao songDao;

    public List<Song> findAll() {
        return songDao.findAll();
    }

    public Optional<Song> findById(Integer id) {
        return songDao.findById(id);
    }

    public boolean delete(Integer id) {
        return songDao.delete(id);
    }

    public void update(Song entity) {
        songDao.update(entity);
    }

    public Song save(Song entity) {
        return songDao.save(entity);
    }

    public boolean deleteAll() {
        return songDao.deleteAll();
    }

}
