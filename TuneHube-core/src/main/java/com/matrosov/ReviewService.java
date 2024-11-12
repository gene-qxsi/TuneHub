package com.matrosov;

import com.matrosov.dao.ReviewDao;
import com.matrosov.entity.Review;

import java.util.List;
import java.util.Optional;

public class ReviewService implements Service<Integer, Review> {
    private final ReviewDao reviewDao = ReviewDao.getInstance();

    @Override
    public List<Review> findAll() {
        return reviewDao.findAll();
    }

    @Override
    public Optional<Review> findById(Integer id) {
        return reviewDao.findById(id);
    }

    @Override
    public boolean delete(Integer id) {
        return reviewDao.delete(id);
    }

    @Override
    public void update(Review entity) {
        reviewDao.update(entity);
    }

    @Override
    public Review save(Review entity) {

        return reviewDao.save(entity);
    }

    @Override
    public boolean deleteAll() {
        return reviewDao.deleteAll();
    }
}
