package com.byhovsky.travelagency.repository;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.entity.Tour;
import com.byhovsky.agency.entity.TourType;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.TourRepositoryImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TourRepositoryTest
 *
 * @author Denis Byhovsky
 */
public class TourRepositoryTest {
    private TourRepositoryImpl tourRepository;
    private Tour tour;
    private Tour tour1;
    private Tour tour3;
    private Country country;
    private Hotel hotel;

    @Before
    public void init() {
        CopyOnWriteArrayList<Tour> tours = new CopyOnWriteArrayList<>();
        country = new Country(1, "Russia");
        hotel = new Hotel(1, "Russia-hotel", "37434", country, 4);
        tour = new Tour(1, "1.jpg", new BigDecimal(343), "For two person", 5, new Date(2017 - 11 - 11), country, TourType.BICUCLE, hotel);
        tour1 = new Tour(2, "2.jpg", new BigDecimal(322), "For one person", 6, new Date(2017 - 07 - 11), country, TourType.BICUCLE, hotel);
        tours.add(tour);
        tours.add(tour1);
        tourRepository = new TourRepositoryImpl(tours);
    }

    @Test
    public void addTourTest() throws RepositoryException {
        tour3 = new Tour(3, "3.jpg", new BigDecimal(322), "For one person", 6, new Date(2017 - 07 - 11), country, TourType.BICUCLE, hotel);
        Optional<Tour> optionalTour = tourRepository.add(tour3);
        Assert.assertTrue(optionalTour.isPresent());
        Assert.assertEquals(tour3, optionalTour.get());
    }

    @Test
    public void readTourTest() {
        Assert.assertNotNull(tourRepository.getAll());
    }

    @Test
    public void updateTourTest() throws RepositoryException {
        tour3 = new Tour(3, "3.jpg", new BigDecimal(322), "For one person", 6, new Date(2017 - 07 - 11), country, TourType.BICUCLE, hotel);
        Optional<Tour> optionalTour = tourRepository.update(0, tour3);
        Assert.assertTrue(optionalTour.isPresent());
        Assert.assertEquals(tour3, optionalTour.get());
    }


    @Test
    public void deleteTourTest() throws RepositoryException {
        Assert.assertTrue(tourRepository.remove(tour));
    }
}
