package com.byhovsky.agency.repository.impl;

import com.byhovsky.agency.entity.Tour;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TourRepositoryImpl provides the methods of
 * manipulating  tours data
 *
 * @author Denis Byhovsky
 */
public class TourRepositoryImpl implements Repository<Tour> {

    private CopyOnWriteArrayList<Tour> tours;

    public TourRepositoryImpl(CopyOnWriteArrayList<Tour> tours) {
        this.tours = tours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Tour> add(Tour tour) throws RepositoryException {
        if (tours.contains(tour)) {
            throw new RepositoryException("Cant add tour");
        } else {
            tours.add(tour);
            return Optional.of(tour);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<Tour> getAll() {
        return tours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Tour tour) throws RepositoryException {
        if (tours.contains(tour)) {
            tours.remove(tour);
            return true;
        } else {
            throw new RepositoryException("Cant remove tour");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Tour> update(int index, Tour tour) throws RepositoryException {
        if (tours.get(index) != null) {
            tours.set(index, tour);
            return Optional.of(tour);
        } else {
            throw new RepositoryException("Cant update tour");
        }
    }
}
