package com.byhovsky.travelagency.service;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.HotelRepositoryImpl;
import com.byhovsky.agency.service.impl.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * HotelServiceTest
 *
 * @author Denis Byhovsky
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class HotelServiceTest {

    @InjectMocks
    private HotelServiceImpl hotelService;
    @Mock
    private HotelRepositoryImpl hotelRepository;

    private CopyOnWriteArrayList<Hotel> hotels;
    private Hotel hotel;
    private Hotel hotel1;
    private Country country;


    @Before
    public void init() {
        hotelService = new HotelServiceImpl(hotelRepository);
        hotels = new CopyOnWriteArrayList<>();
        country = new Country(1, "Russia");
        hotel = new Hotel(1, "Russia-hotel", "37434", country, 4);
        hotel1 = new Hotel(2, "Belarus-hotel", "33243", country, 3);

        hotels.add(hotel);
        hotels.add(hotel1);

    }

    @Test
    public void addHotelTest() throws RepositoryException {
        when(hotelRepository.add(hotel)).thenReturn(Optional.of(hotel));
        assertEquals(hotel, hotelService.create(hotel));
    }

    @Test
    public void addHotelVerTets() throws RepositoryException {
        hotelRepository.add(hotel);
        verify(hotelRepository, times(1)).add(hotel);
    }

    @Test
    public void updateHotelTest() throws RepositoryException {
        when(hotelRepository.update(0, hotel)).thenReturn(Optional.of(hotel));
        assertEquals(hotel, hotelService.update(0, hotel));
    }

    @Test
    public void updateHotelVerTets() throws RepositoryException {
        hotelRepository.update(0, hotel);
        verify(hotelRepository, times(1)).update(0, hotel);
    }

    @Test
    public void deleteHotelTest() throws RepositoryException {
        when(hotelRepository.remove(hotel)).thenReturn(true);
        assertTrue(hotelService.delete(hotel));
    }

    @Test
    public void deleteHotelVerTets() throws RepositoryException {
        hotelRepository.remove(hotel);
        verify(hotelRepository, times(1)).remove(hotel);
    }
}

