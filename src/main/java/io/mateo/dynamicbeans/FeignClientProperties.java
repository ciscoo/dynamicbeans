package io.mateo.dynamicbeans;

import lombok.Data;

@Data
public class FeignClientProperties {
    private String url;
    private Class<?> apiType;
}
