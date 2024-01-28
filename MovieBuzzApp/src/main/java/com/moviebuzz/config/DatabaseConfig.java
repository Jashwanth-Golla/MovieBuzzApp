package com.moviebuzz.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class DatabaseConfig {

    private static final int MAX_RETRY_ATTEMPTS = 5;
    private static final long RETRY_DELAY_MS = 3000; // 3 seconds delay between retries

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://database:3306/movie-buzz");
        dataSourceBuilder.username("admin");
        dataSourceBuilder.password("root");
        return configureDataSourceWithRetry(dataSourceBuilder);
    }

    private DataSource configureDataSourceWithRetry(DataSourceBuilder dataSourceBuilder) {
        int attempts = 0;
        while (attempts < MAX_RETRY_ATTEMPTS) {
            try {
                return dataSourceBuilder.build();
            } catch (Exception e) {
                System.err.println("Failed to connect to the database. Retrying...");
                attempts++;
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new RuntimeException("Failed to connect to the database after multiple attempts.");
    }
}

