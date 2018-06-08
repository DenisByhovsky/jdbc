package com.byhovsky.travelagency.repository;

import com.byhovsky.agency.entity.*;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.ReviewRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ReviewRepositoryTest
 *
 * @author Denis Byhovsky
 */
public class ReviewRepositoryTest {

    private ReviewRepositoryImpl reviewRepository;
    private Review review;
    private Review review1;
    private Review review3;
    private Tour tour;
    private Country country;
    private Hotel hotel;

    @Before
    public void init() {
        CopyOnWriteArrayList<Review> reviews = new CopyOnWriteArrayList<>();
        country = new Country(1, "Russia");
        hotel = new Hotel(1, "Russia-hotel", "37434", country, 4);
        tour = new Tour(1, "1.jpg", new BigDecimal(343), "For two person", 5, new Date(2017 - 11 - 11), country, TourType.BICUCLE, hotel);
        review = new Review(1, "good", tour);
        review1 = new Review(2, "perfect", tour);
        reviews.add(review);
        reviews.add(review1);
        reviewRepository = new ReviewRepositoryImpl(reviews);
    }

    @Test
    public void addReviewTest() throws RepositoryException {
        review3 = new Review(3, "so-so", tour);
        Optional<Review> optionalReview = reviewRepository.add(review3);
        Assert.assertTrue(optionalReview.isPresent());
        Assert.assertEquals(review3, optionalReview.get());
    }

    @Test
    public void readReviewTest() {
        Assert.assertNotNull(reviewRepository.getAll());
    }

    @Test
    public void updateReviewTest() throws RepositoryException {
        review3 = new Review(3, "so-so", tour);
        Optional<Review> optionalReview = reviewRepository.update(0, review3);
        Assert.assertTrue(optionalReview.isPresent());
        Assert.assertEquals(review3, optionalReview.get());
    }

    @Test
    public void deleteReviewTest() throws RepositoryException {
        Assert.assertTrue(reviewRepository.remove(review));
    }
}
