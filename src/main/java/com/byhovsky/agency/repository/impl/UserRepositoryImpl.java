package com.byhovsky.agency.repository.impl;

import com.byhovsky.agency.entity.User;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * UserRepositoryImpl provides the methods of
 * manipulating  users  data
 *
 * @author Denis Byhovsky
 */
public class UserRepositoryImpl implements Repository<User> {

    private CopyOnWriteArrayList<User> users;

    public UserRepositoryImpl(CopyOnWriteArrayList<User> users) {
        this.users = users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> add(User user) throws RepositoryException {
        if (!users.contains(user)) {
            users.add(user);
            return Optional.of(user);

        } else {
            throw new RepositoryException("Cant add review");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CopyOnWriteArrayList<User> getAll() {
        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(User user) throws RepositoryException {
        if (users.contains(user)) {
            users.remove(user);
            return true;
        } else {
            throw new RepositoryException("Cant remove user");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> update(int index, User user) throws RepositoryException {
        if (users.get(index) != null) {
            users.set(index, user);
            return Optional.of(user);
        } else {
            throw new RepositoryException("Cant update user");
        }
    }
}
