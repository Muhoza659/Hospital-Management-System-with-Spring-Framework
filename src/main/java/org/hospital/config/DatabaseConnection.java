package org.hospital.config;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnection {

    private static final String url = "jdbc:postgresql://localhost:5432/hospital_management";
    private static final String userName = "postgres";
    private static final String password = "1234Rosine.";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
}
