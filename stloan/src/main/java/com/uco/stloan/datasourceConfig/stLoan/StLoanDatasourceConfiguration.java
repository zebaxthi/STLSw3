package com.uco.stloan.datasourceConfig.stLoan;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class StLoanDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.stLoan")
    public DataSourceProperties stLoanDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource stLoanDataSource() {
        return stLoanDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
