package com.byhovsky.agency.repository.impl;

import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * HotelRepositoryImpl provides the methods of
 * manipulating  hotels data
 *
 * @author Denis Byhovsky
 */
public class HotelRepositoryImpl implements Repository<Hotel> {

    private CopyOnWriteArrayList<Hotel> hotels;

    public HotelRepositoryImpl(CopyOnWriteArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Hotel> add(Hotel hotel) throws RepositoryException {
        if (hotels.contains(hotel)) {
            throw new RepositoryException("Cant add hotel");
        } else {
            hotels.add(hotel);
            return Optional.of(hotel);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<Hotel> getAll() {
        return hotels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Hotel hotel) throws RepositoryException {
        if (hotels.contains(hotel)) {
            hotels.remove(hotel);
            return true;
        } else {
            throw new RepositoryException("Cant remove hotel");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Hotel> update(int index, Hotel hotel) throws RepositoryException {
        if (hotels.get(index) != null) {
            hotels.set(index, hotel);
            return Optional.of(hotel);
        } else {
            throw new RepositoryException("Cant update hotel");
        }
    }
}
