package com.byhovsky.agency.connection;

import com.byhovsky.agency.exception.ConnectionPoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock();
    private static AtomicBoolean active = new AtomicBoolean(false);
    private static AtomicBoolean closed = new AtomicBoolean(false);
    private final String URL;
    private final Properties PROP;
    private final int MAX_WAITING_TIME = 1000;
    private int poolSize;
    private ArrayBlockingQueue<ProxyConnection> connections;


    private ConnectionPool() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        poolSize = Integer.valueOf(resource.getString("db.poolsize"));
        URL = resource.getString("db.url");
        PROP = new Properties();
        PROP.put("user", resource.getString("db.user"));
        PROP.put("password", resource.getString("db.password"));
        PROP.put("autoReconnect", resource.getString("db.autoReconnect"));
        PROP.put("characterEncoding", resource.getString("db.encoding"));
        PROP.put("useUnicode", resource.getString("db.useUnicode"));
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOG.warn("Exception while driver register");
            throw new RuntimeException("Error while driver register");
        }
        connections = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            addConnection();
        }
        if (connections.size() == 0) {
            LOG.warn("Error in connection pool! Connection didn't created");
            throw new RuntimeException("Error in pool creation");
        }
    }

    /**
     * Getter for property 'instance'.
     *
     * @return Value for property 'instance'.
     */
    public static ConnectionPool getInstance() {
        if (!active.get()) {
            lock.lock();
            if (!active.get()) {
                try {
                    instance = new ConnectionPool();
                    active.set(true);
                } finally {
                    lock.unlock();
                }
            }
        }
        return instance;
    }

    private void addConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, PROP);
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            connections.add(proxyConnection);
        } catch (SQLException e) {
            LOG.error("Sql Exception " + e);
        }
    }

    /**
     * Getter for property 'connection'.
     *
     * @return Value for property 'connection'.
     */
    public ProxyConnection getConnection() throws ConnectionPoolException {
        if (!closed.get()) {
            try {
                ProxyConnection connection = connections.poll(MAX_WAITING_TIME, TimeUnit.MILLISECONDS);
                if (connection != null) {
                    return connection;
                } else {
                    throw new ConnectionPoolException("Timeout! Can not poll connection!");
                }
            } catch (InterruptedException e) {
                LOG.error("Interrupted exception", e);
            }
        }
        throw new ConnectionPoolException("Pool is free. Can not get connection");
    }

    public void returnConnection(ProxyConnection connection) {
        if (connection != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.rollback();
                }
                connections.add(connection);
            } catch (SQLException e) {
                LOG.error("Can not return connection to pool", e);
            }
        }
    }

    public void destroyPool() {
        closed = new AtomicBoolean(true);
        for (ProxyConnection pc : connections) {
            try {
                pc.destroy();
            } catch (SQLException e) {
                LOG.error("Can not destroy connection");
            }
        }
    }

    /**
     * Getter for property 'poolSize'.
     *
     * @return Value for property 'poolSize'.
     */
    public int getPoolSize() {
        return poolSize;
    }

    /**
     * Getter for property 'connectionCount'.
     *
     * @return Value for property 'connectionCount'.
     */
    public int getConnectionCount() {
        return connections.size();
    }
}
