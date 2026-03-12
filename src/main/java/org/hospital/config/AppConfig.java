package org.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@ComponentScan("org.hospital")
public class AppConfig {

    @Bean
    public Connection getConnection() throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        return connection.getConnection();
    }
}