package com.teamconnect.configuration;

import com.couchbase.client.core.env.TimeoutConfig;
import com.couchbase.client.java.env.ClusterEnvironment;
import java.time.Duration;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.convert.CustomConversions;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {
    @Value("${spring.couchbase.connection-string}")
    private String connectionString;

    @Value("${spring.couchbase.username}")
    private String username;

    @Value("${spring.couchbase.password}")
    private String password;

    @Value("${spring.couchbase.bucket-name}")
    private String bucketName;

    @Override
    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }

    @Override
    protected void configureEnvironment(ClusterEnvironment.Builder builder) {
        builder.timeoutConfig(TimeoutConfig.kvTimeout(Duration.ofSeconds(5))
            .connectTimeout(Duration.ofSeconds(10))
            .queryTimeout(Duration.ofSeconds(10))
            .managementTimeout(Duration.ofSeconds(10))
            .searchTimeout(Duration.ofSeconds(10)));
    }

    @Bean
    @Qualifier("couchbaseCustomConversions")
    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(Collections.emptyList());
    }
}
