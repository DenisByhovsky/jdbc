package com.byhovsky.agency.jdbc;

import com.byhovsky.agency.connection.ConnectionPool;
import com.byhovsky.agency.connection.ProxyConnection;
import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.exception.ServiceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryTemplate {

    private static final String CREATE_COUNTRY = "INSERT INTO country(country_name) VALUES (?)";


    private static final String UPDATE_COUNTRY = "UPDATE  country SET country_name = ? WHERE country_id =?";

    private static final String DELETE_COUNTRY = "DELETE FROM country WHERE country_id = ?";

    private static final String FIND_ALL_COUNTRY = "SELECT country_id, country_name  FROM country";

    public void create(Country country) {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = proxyConnection.prepareStatement(CREATE_COUNTRY)) {
            preparedStatement.setString(1, country.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException | ServiceException e) {
            throw new ServiceException("Error in add new country" + e);
        }
    }

    public void delete(int id) {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = proxyConnection.prepareStatement(DELETE_COUNTRY)) {
            preparedStatement.setString(1, null);
            preparedStatement.executeUpdate();
        } catch (SQLException | ServiceException e) {
            throw new ServiceException("Error in add new country" + e);
        }


    }

    public void update(Country country) {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = proxyConnection.prepareStatement(UPDATE_COUNTRY)) {
            preparedStatement.setString(1, country.getName());
            preparedStatement.setString(2, String.valueOf(country.getId()));
            preparedStatement.executeUpdate();
        } catch (SQLException | ServiceException e) {
            throw new ServiceException("Error in add new country" + e);
        }
    }


    public ArrayList<Country> takeAll() throws SQLException {
        ArrayList<Country> countries = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_COUNTRY)) {
            ResultSet resultSet = statement.executeQuery();
            Country country;
            while (resultSet.next()) {
                country = new Country();
                country.setId(Integer.parseInt(resultSet.getString("COUNTRY_ID")));
                country.setName(resultSet.getString("COUNTRY_NAME"));
                countries.add(country);
            }
        }
        return countries;
    }
}