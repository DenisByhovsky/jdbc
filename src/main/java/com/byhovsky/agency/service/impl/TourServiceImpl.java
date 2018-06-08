package com.byhovsky.agency.service.impl;

import com.byhovsky.agency.entity.Tour;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.TourRepositoryImpl;
import com.byhovsky.agency.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TourServiceImpl  provides the methods of
 * service layer
 *
 * @author Denis Byhovsky
 */
public class TourServiceImpl implements Service<Tour> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceImpl.class.getName());

    private CopyOnWriteArrayList<Tour> tours;
    private TourRepositoryImpl repository;

    public TourServiceImpl(TourRepositoryImpl repository) {
        tours = new CopyOnWriteArrayList<>();
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tour create(Tour tour) {
        Optional<Tour> optionalTour = repository.add(tour);
        LOGGER.info("Tour was created successfully");
        return optionalTour.orElseThrow(
                () -> new RepositoryException("Cant create tour in tour create method")
        );

    }

    /**
     * Update Tour description
     *
     * @param index
     * @param newDescription
     */
    public void updateDescription(int index, String newDescription) {
        tours.get(index).setDescription(newDescription);
        LOGGER.info("Description was updated successfully");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tour update(int index, Tour tour) {
        Optional<Tour> optionalTour = repository.update(index, tour);
        LOGGER.info("Tour was updated successfully");
        return optionalTour.orElseThrow(
                () -> new RepositoryException("Cant update tour in tour update method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Tour tour) throws RepositoryException {
        repository.remove(tour);
        LOGGER.info("Tour was deleted successfully");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<Tour> read() {
        return repository.getAll();
    }
}
