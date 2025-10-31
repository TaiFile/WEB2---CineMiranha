package br.ufscar.pooa.cinema_api.infrastructure;

import framework.FrameworkClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("myFramework")
@Configuration
public class MyFrameworkConfig {
    @Value( "${spring.datasource.url}")
    private String dbUrl;

    @Value( "${spring.datasource.username}")
    private String dbUser;

    @Value( "${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public FrameworkClass frameworkClass(){
        return new FrameworkClass(dbUrl, dbUser, dbPassword);
    }

}
