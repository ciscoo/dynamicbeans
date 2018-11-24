package io.mateo.dynamicbeans;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class FeignClientFactory {
    private final JacksonEncoder encoder = new JacksonEncoder();
    private final JacksonDecoder decoder = new JacksonDecoder();

    public Object create(FeignClientProperties properties) {
        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .target(properties.getApiType(), properties.getUrl());
    }
}
