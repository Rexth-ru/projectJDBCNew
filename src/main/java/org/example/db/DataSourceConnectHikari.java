package org.example.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConnectHikari {
    private HikariConfig config;
    private HikariDataSource ds;

    public DataSourceConnectHikari() {
        config = new HikariConfig("db.properties");
        ds = new HikariDataSource(config);
    }

    public DataSourceConnectHikari(HikariConfig config) {
        this.config = config;
        this.ds = new HikariDataSource(config);
    }


    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = ds.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return connection;
    }
}


