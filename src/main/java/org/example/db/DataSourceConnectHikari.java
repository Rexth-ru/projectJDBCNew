package org.example.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConnectHikari {
    private static final HikariConfig config = new HikariConfig("db.properties");
    private final transient HikariDataSource ds = new HikariDataSource( config );

//    private DataSourceConnectHikari(HikariDataSource ds) {
//        this.ds = ds;
//    }
    public  Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
