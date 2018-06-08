package com.byhovsky.agency.repository.impl;

import com.byhovsky.agency.entity.Review;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ReviewRepositoryImpl provides the methods of
 * manipulating  reviews  data
 *
 * @author Denis Byhovsky
 */
public class ReviewRepositoryImpl implements Repository<Review> {

    private CopyOnWriteArrayList<Review> reviews;

    public ReviewRepositoryImpl(CopyOnWriteArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Review> add(Review review) throws RepositoryException {
        if (reviews.contains(review)) {
            throw new RepositoryException("Cant add review");
        } else {
            reviews.add(review);
            return Optional.of(review);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<Review> getAll() {
        return reviews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Review review) throws RepositoryException {
        if (reviews.contains(review)) {
            reviews.remove(review);
            return true;
        } else {
            throw new RepositoryException("Cant remove review");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Review> update(int index, Review review) throws RepositoryException {
        if (reviews.get(index) != null) {
            reviews.set(index, review);
            return Optional.of(review);
        } else {
            throw new RepositoryException("Cant update review");
        }
    }
}
