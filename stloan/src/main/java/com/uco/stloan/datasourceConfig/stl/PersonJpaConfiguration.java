package com.uco.stloan.datasourceConfig.stl;

import com.uco.stloan.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = Person.class,
        entityManagerFactoryRef = "personEntityManagerFactory",
        transactionManagerRef = "personTransactionManager"
)
public class PersonJpaConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean personEntityManagerFactory(
            @Qualifier("stlDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource)
                .packages(Person.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager personTransactionManager(
            @Qualifier("personEntityManagerFactory") LocalContainerEntityManagerFactoryBean personEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(personEntityManagerFactory.getObject()));
    }
}
