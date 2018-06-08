package com.byhovsky.agency.service.impl;

import com.byhovsky.agency.entity.Review;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.ReviewRepositoryImpl;
import com.byhovsky.agency.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ReviewServiceImpl  provides the methods of
 * service layer
 *
 * @author Denis Byhovsky
 */
public class ReviewServiceImpl implements Service<Review> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class.getName());

    private CopyOnWriteArrayList<Review> reviews;
    private ReviewRepositoryImpl repository;

    public ReviewServiceImpl(ReviewRepositoryImpl repository) {
        reviews = new CopyOnWriteArrayList<>();
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Review create(Review review) {
        Optional<Review> optionalReview = repository.add(review);
        LOGGER.info("Review was created successfully");
        return optionalReview.orElseThrow(
                () -> new RepositoryException("Cant create review in review update method")
        );
    }


    public void updateReviewID(int index, int newId) {
        reviews.get(index).setReviewId(newId);
        LOGGER.info("Reviews id  was updated successfully");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Review update(int index, Review review) {
        Optional<Review> optionalReview = repository.update(index, review);
        LOGGER.info("Review was updated successfully");
        return optionalReview.orElseThrow(
                () -> new RepositoryException("Cant update review in review update method")
        );


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Review review) throws RepositoryException {
        repository.remove(review);
        LOGGER.info("Review was deleted successfully");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<Review> read() {
        return repository.getAll();
    }


}
