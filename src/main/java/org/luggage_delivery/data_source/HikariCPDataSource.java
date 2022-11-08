package org.luggage_delivery.data_source;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 6:13
*/

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPDataSource {
    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource cpDataSource;

    private HikariCPDataSource() {}

    public static Connection getConnection() throws SQLException {
        if (cpDataSource != null)
            return cpDataSource.getConnection();

        synchronized (HikariCPDataSource.class) {
            if (cpDataSource != null)
                return cpDataSource.getConnection();

            config.setJdbcUrl("jdbc:mysql://localhost:3306/luggage_delivery");
            config.setUsername("root");
            config.setPassword("root");
            config.setMinimumIdle(5);
            config.setMaximumPoolSize(20);
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            cpDataSource = new HikariDataSource(config);
        }

        return cpDataSource.getConnection();
    }
}
