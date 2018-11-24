package io.mateo.dynamicbeans;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Data
@Validated
@ConfigurationProperties("app")
public class FeignConfigurationProperties {
    private final DownstreamServices downstreamServices = new DownstreamServices();

    @Data
    public static class DownstreamServices {
        private Map<String, FeignClientProperties> rest = new HashMap<>();
    }
}
