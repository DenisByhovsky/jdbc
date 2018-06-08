package com.byhovsky.agency.repository.impl;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CountryRepositoryImpl provides the methods of
 * manipulating  countries data
 *
 * @author Denis Byhovsky
 */
public class CountryRepositoryImpl implements Repository<Country> {

    private CopyOnWriteArrayList<Country> countries;

    public CountryRepositoryImpl(CopyOnWriteArrayList<Country> countries) {
        this.countries = countries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Country> add(Country country) {
        if (!countries.contains(country)) {
            countries.add(country);
            return Optional.of(country);
        } else {
            throw new RepositoryException("Cant create country");
        }

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<Country> getAll() {
        return countries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Country country) {
        if (countries.contains(country)) {
            countries.remove(country);
            return true;
        } else {
            throw new RepositoryException("Cant remove");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Country> update(int index, Country country) {
        if (countries.get(index) != null) {
            countries.set(index, country);
            return Optional.of(country);
        } else {
            throw new RepositoryException("Cant update");
        }
    }
}
