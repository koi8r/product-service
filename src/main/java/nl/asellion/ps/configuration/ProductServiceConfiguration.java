package nl.asellion.ps.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.classmate.TypeResolver;

import nl.asellion.ps.jparepository.ProductJpaEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring boot entry point configuration class
 *
 * @author Alexander Kirillov
 */

@Configuration
@ComponentScan("nl.asellion.ps")
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

    /**
     * Swagger Docket
     *
     * @return
     */

    private final TypeResolver typeResolver;

    @Autowired
    public ProductServiceConfiguration(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("nl.asellion.ps.api.controller"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .additionalModels(typeResolver.resolve(ProductJpaEntity.class))
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Product Service REST API",
                "Basic end points for Product Service ",
                "API TOS",
                null,
                new Contact("Alexander Kirillov", "https://github.com/koi8r", "alexander.kirillov.work@gmail.com"),
                null, null,
                Collections.emptyList());
    }

}
