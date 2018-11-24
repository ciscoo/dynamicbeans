package io.mateo.dynamicbeans;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FeignConfigurationProperties.class)
public class FeignClientAutoConfiguration {
    @Bean
    public FeignClientFactory feignClientFactory() {
        return new FeignClientFactory();
    }
}
