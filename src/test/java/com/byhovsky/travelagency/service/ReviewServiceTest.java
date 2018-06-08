package com.byhovsky.travelagency.service;

import com.byhovsky.agency.entity.*;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.ReviewRepositoryImpl;
import com.byhovsky.agency.service.impl.ReviewServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * ReviewServiceTest
 *
 * @author Denis Byhovsky
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ReviewServiceTest {

    @InjectMocks
    private ReviewServiceImpl reviewService;
    @Mock
    private ReviewRepositoryImpl reviewRepository;

    private CopyOnWriteArrayList<Review> reviews;
    private Review review;
    private Review review1;
    private Tour tour;
    private Country country;
    private Hotel hotel;


    @Before
    public void init() {
        reviewService = new ReviewServiceImpl(reviewRepository);
        reviews = new CopyOnWriteArrayList<>();
        country = new Country(1, "Russia");
        hotel = new Hotel(1, "Russia-hotel", "37434", country, 4);
        tour = new Tour(1, "1.jpg", new BigDecimal(343), "For two person", 5, new Date(2017 - 11 - 11), country, TourType.BICUCLE, hotel);
        review = new Review(1, "good", tour);
        review1 = new Review(2, "perfect", tour);
        reviews.add(review);
        reviews.add(review1);

    }

    @Test
    public void addReviewTest() throws RepositoryException {
        when(reviewRepository.add(review)).thenReturn(Optional.of(review));
        assertEquals(review, reviewService.create(review));
    }

    @Test
    public void addReviewVerTets() throws RepositoryException {
        reviewRepository.add(review);
        verify(reviewRepository, times(1)).add(review);
    }


    @Test
    public void updateReviewTest() throws RepositoryException {
        when(reviewRepository.update(0, review)).thenReturn(Optional.of(review));
        assertEquals(review, reviewService.update(0, review));
    }


    @Test
    public void updateReviewVerTets() throws RepositoryException {
        reviewRepository.update(0, review);
        verify(reviewRepository, times(1)).update(0, review);
    }

    @Test
    public void deleteReviewTest() throws RepositoryException {
        when(reviewRepository.remove(review)).thenReturn(true);
        assertTrue(reviewService.delete(review));
    }

    @Test
    public void deleteReviewVerTets() throws RepositoryException {
        reviewRepository.remove(review);
        verify(reviewRepository, times(1)).remove(review);
    }
}
