package com.byhovsky.agency.service.impl;

import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.HotelRepositoryImpl;
import com.byhovsky.agency.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * HotelServiceImpl  provides the methods of
 * service layer
 *
 * @author Denis Byhovsky
 */
public class HotelServiceImpl implements Service<Hotel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelServiceImpl.class.getName());

    private HotelRepositoryImpl repository;

    public HotelServiceImpl(HotelRepositoryImpl repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hotel create(Hotel hotel) {
        Optional<Hotel> optionalHotel = repository.add(hotel);
        LOGGER.info("Hotel was created successfully");
        return optionalHotel.orElseThrow(
                () -> new RepositoryException("Cant create hotel in hotel create method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hotel update(int index, Hotel hotel) {
        Optional<Hotel> optionalHotel = repository.update(index, hotel);
        LOGGER.info("Hotel was updated successfully");
        return optionalHotel.orElseThrow(
                () -> new RepositoryException("Cant update hotel in hotel update method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Hotel hotel) throws RepositoryException {
        repository.remove(hotel);
        LOGGER.info("Hotel was deleted successfully");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<Hotel> read() {
        return repository.getAll();
    }

}
