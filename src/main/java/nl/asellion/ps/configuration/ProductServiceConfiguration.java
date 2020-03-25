package nl.asellion.ps.configuration;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring boot entry point configuration class
 *
 * @author Alexander Kirillov
 */

@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableSwagger2
public class ProductServiceConfiguration {

    /**
     * A bean to enable httptrace from actuator
     *
     * @return InMemoryHttpTraceRepository
     */
    @Bean
    public HttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }
}
