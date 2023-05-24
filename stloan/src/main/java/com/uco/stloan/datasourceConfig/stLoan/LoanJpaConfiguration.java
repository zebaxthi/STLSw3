package com.uco.stloan.datasourceConfig.stLoan;

import com.uco.stloan.model.Loan;
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
        basePackageClasses = Loan.class,
        entityManagerFactoryRef = "loanEntityManagerFactory",
        transactionManagerRef = "loanTransactionManager"
)
public class LoanJpaConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean loanEntityManagerFactory(
            @Qualifier("stLoanDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource)
                .packages(Loan.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager loanTransactionManager(
            @Qualifier("loanEntityManagerFactory") LocalContainerEntityManagerFactoryBean loanEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(loanEntityManagerFactory.getObject()));
    }
}
