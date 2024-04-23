package io.bootify.musica_api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.bootify.musica_api.domain")
@EnableJpaRepositories("io.bootify.musica_api.repos")
@EnableTransactionManagement
public class DomainConfig {
}
