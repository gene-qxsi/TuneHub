package com.matrosov.service;

import com.matrosov.dao.UserDao;
import com.matrosov.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findById(Integer id) {
        return userDao.findById(id);
    }

    public boolean delete(Integer id) {
        return userDao.delete(id);
    }

    public void update(User entity) {
        userDao.update(entity);
    }

    public User save(User entity) {
        return userDao.save(entity);
    }
}
