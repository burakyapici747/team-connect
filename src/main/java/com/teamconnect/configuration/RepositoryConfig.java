package com.teamconnect.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.teamconnect.repository.postgresql"
)
@EnableCouchbaseRepositories(
    basePackages = "com.teamconnect.repository.couchbase"
)
public class RepositoryConfig {
}
