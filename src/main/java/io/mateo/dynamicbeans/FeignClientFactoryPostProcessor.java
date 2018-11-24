package io.mateo.dynamicbeans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class FeignClientFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    private final FeignConfigurationProperties properties;
    private final FeignClientFactory feignClientFactory;

    public FeignClientFactoryPostProcessor(FeignConfigurationProperties properties, FeignClientFactory feignClientFactory) {
        this.properties = properties;
        this.feignClientFactory = feignClientFactory;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        properties.getDownstreamServices().getRest().forEach((beanName, props) -> makeClient(beanName, props, registry));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // no-op
    }

    private void makeClient(String beanName, FeignClientProperties props, BeanDefinitionRegistry registry) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(props.getApiType());
        beanDefinition.setInstanceSupplier(() -> feignClientFactory.create(props));
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
