package com.byhovsky.travelagency.service;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.entity.Tour;
import com.byhovsky.agency.entity.TourType;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.TourRepositoryImpl;
import com.byhovsky.agency.service.impl.TourServiceImpl;
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
 * TourServiceTest
 *
 * @author Denis Byhovsky
 */

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class TourServiceTest {

    @InjectMocks
    private TourServiceImpl tourService;
    @Mock
    private TourRepositoryImpl tourRepository;

    private CopyOnWriteArrayList<Tour> tours;
    private Tour tour;
    private Tour tour1;
    private Country country;
    private Hotel hotel;

    @Before
    public void init() {
        tourService = new TourServiceImpl(tourRepository);
        tours = new CopyOnWriteArrayList<>();
        country = new Country(1, "Russia");
        hotel = new Hotel(1, "Russia-hotel", "37434", country, 4);
        tour = new Tour(1, "1.jpg", new BigDecimal(343), "For two person", 5, new Date(2017 - 11 - 11), country, TourType.BICUCLE, hotel);
        tour1 = new Tour(2, "2.jpg", new BigDecimal(322), "For one person", 6, new Date(2017 - 07 - 11), country, TourType.BICUCLE, hotel);
        tours.add(tour1);
    }

    @Test
    public void addTourTest() throws RepositoryException {
        when(tourRepository.add(tour)).thenReturn(Optional.of(tour));
        assertEquals(tour, tourService.create(tour));
    }

    @Test
    public void addTourVerTets() throws RepositoryException {
        tourRepository.add(tour);
        verify(tourRepository, times(1)).add(tour);
    }

    @Test
    public void updateTourTest() throws RepositoryException {
        when(tourRepository.update(0, tour)).thenReturn(Optional.of(tour));
        assertEquals(tour, tourService.update(0, tour));
    }

    @Test
    public void updateTourVerTets() throws RepositoryException {
        tourRepository.update(0, tour);
        verify(tourRepository, times(1)).update(0, tour);
    }

    @Test
    public void deleteTourTest() throws RepositoryException {
        when(tourRepository.remove(tour)).thenReturn(true);
        assertTrue(tourService.delete(tour));
    }

    @Test
    public void deleteTourVerTets() throws RepositoryException {
        tourRepository.remove(tour);
        verify(tourRepository, times(1)).remove(tour);
    }
}
