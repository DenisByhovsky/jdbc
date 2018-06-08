package com.byhovsky.travelagency.service;

import com.byhovsky.agency.entity.User;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.exception.ServiceException;
import com.byhovsky.agency.repository.impl.UserRepositoryImpl;
import com.byhovsky.agency.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * UserServiceTest
 *
 * @author Denis Byhovsky
 */

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepositoryImpl userRepository;

    private CopyOnWriteArrayList<User> users;
    private User user;
    private User user1;

    @Before
    public void init() {
        userService = new UserServiceImpl(userRepository);
        users = new CopyOnWriteArrayList<>();
        char[] pass = new char[]{'K', 'E', 'V', 'I', 'N'};
        user = new User(2, "newAccc11", pass);
        user1 = new User(1, "newAcccww", pass);
        users.add(user1);
    }

    @Test
    public void addUserTest() throws RepositoryException, ServiceException {
        when(userRepository.add(user)).thenReturn(Optional.of(user));
        assertEquals(user, userService.create(user));
    }

    @Test
    public void updateUserTest() throws RepositoryException, ServiceException {
        when(userRepository.update(0, user)).thenReturn(Optional.of(user));
        assertEquals(user, userService.update(0, user));
    }

    @Test
    public void deleteUserTest() throws RepositoryException {
        when(userRepository.remove(user)).thenReturn(true);
        assertTrue(userService.delete(user));
    }
}
