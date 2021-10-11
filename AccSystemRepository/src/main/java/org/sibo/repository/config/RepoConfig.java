package org.sibo.repository.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("org.sibo.repository.persistence")
@EntityScan("org.sibo.domain.persistence")
@PropertySource(value = "classpath:repoprops.properties")
public class RepoConfig {
}
