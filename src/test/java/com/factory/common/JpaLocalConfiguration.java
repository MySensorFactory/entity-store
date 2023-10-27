package com.factory.common;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.factory.persistence.data.repository",
        "com.factory.persistence.users.repository"
})
@EntityScan({
        "com.factory.persistence.data.entity",
        "com.factory.persistence.users.entity"
})
public class JpaLocalConfiguration {
}
