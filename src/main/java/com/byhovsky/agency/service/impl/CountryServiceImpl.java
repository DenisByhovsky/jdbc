package com.byhovsky.agency.service.impl;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.CountryRepositoryImpl;
import com.byhovsky.agency.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * CountryServiceImpl  provides the methods of
 * service layer
 *
 * @author Denis Byhovsky
 */
public class CountryServiceImpl implements Service<Country> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class.getName());

    private CopyOnWriteArrayList<Country> countries;
    private CountryRepositoryImpl repository;

    public CountryServiceImpl(CountryRepositoryImpl repository) {
        this.repository = repository;
        countries = new CopyOnWriteArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country create(Country country) {
        Optional<Country> optionalCountry = repository.add(country);
        LOGGER.info("Country was created successfully");
        return optionalCountry.orElseThrow(
                () -> new RepositoryException("Cant create country in country create method")
        );
    }


    public void updateCountryID(int index, int newId) {
        countries.get(index).setId(newId);
        LOGGER.info("Country id was updated successfully");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country update(int index, Country country) {
        Optional<Country> optionalCountry = repository.update(index, country);
        LOGGER.info("Country was updated successfully");
        return optionalCountry.orElseThrow(
                () -> new RepositoryException("Cant update country in country update method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Country country) throws RepositoryException {
        LOGGER.info("Country was deleted successfully");
        return repository.remove(country);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<Country> read() {
        return repository.getAll();
    }
}
