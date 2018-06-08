package com.byhovsky.travelagency.service;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.CountryRepositoryImpl;
import com.byhovsky.agency.service.impl.CountryServiceImpl;
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
 * CountryServiceTest
 *
 * @author Denis Byhovsky
 */

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CountryServiceTest {

    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private CountryRepositoryImpl countryRepository;

    private CopyOnWriteArrayList<Country> countries;
    private Country country;
    private Country country1;
    private Country country2;
    private Country country3;


    @Before
    public void init() {
        countries = new CopyOnWriteArrayList<>();
        country = new Country(1, "Germany");
        country1 = new Country(2, "Spain");
        country2 = new Country(3, "Poland");
        country3 = new Country(4, "Turkey");
        countries.add(country);
        countries.add(country1);
        countries.add(country2);
        countries.add(country3);
    }

    @Test
    public void addCountryTest() throws RepositoryException {
        when(countryRepository.add(country)).thenReturn(Optional.of(country));
        assertEquals(country, countryService.create(country));
    }

    @Test
    public void addCountryVerTets() throws RepositoryException {
        countryRepository.add(country);
        verify(countryRepository, times(1)).add(country);
    }

    @Test
    public void updateCountryTest() throws RepositoryException {
        when(countryRepository.update(0, country)).thenReturn(Optional.of(country));
        assertEquals(country, countryService.update(0, country));
    }

    @Test
    public void updateCountryVerTets() throws RepositoryException {
        countryRepository.update(0, country);
        verify(countryRepository, times(1)).update(0, country);
    }

    @Test
    public void deleteCountryTest() throws RepositoryException {
        when(countryRepository.remove(country)).thenReturn(true);
        assertTrue(countryService.delete(country));
    }

    @Test
    public void deleteCountryVerTets() throws RepositoryException {
        countryRepository.remove(country);
        verify(countryRepository, times(1)).remove(country);
    }
}
