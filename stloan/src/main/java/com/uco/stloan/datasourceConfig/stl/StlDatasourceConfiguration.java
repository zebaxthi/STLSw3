package com.uco.stloan.datasourceConfig.stl;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class StlDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.stl")
    public DataSourceProperties stlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource stlDataSource() {
        return stlDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
