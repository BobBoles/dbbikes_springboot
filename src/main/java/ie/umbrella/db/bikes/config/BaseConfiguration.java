package ie.umbrella.db.bikes.config;
import ie.umbrella.db.bikes.repository.RecordedResponseRepository;
import ie.umbrella.db.bikes.service.DBAPIService;
import ie.umbrella.db.bikes.service.DBAPIServiceImpl;
import ie.umbrella.db.bikes.service.DBAPIServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * Bean to configure if api or recorded data is retrieved by the service layer
 * api - the JCDecaux api is used
 * recorded - previously recorded information is retrieved from the database
 *
 * Which profile is active can be configured in application.properties e.g.
 * spring.profiles.active=recorded
 *
 */

@Configuration
public class BaseConfiguration {

    @Autowired
    RecordedResponseRepository recordedResponseRepository;
    @Bean
    @Profile("api")
    public DBAPIService getAPIService() {
        return new DBAPIServiceImpl();
    }

    @Bean
    @Profile("recorded")
    public DBAPIService getProxyAPIService() {
        return new DBAPIServiceProxy();
    }
}