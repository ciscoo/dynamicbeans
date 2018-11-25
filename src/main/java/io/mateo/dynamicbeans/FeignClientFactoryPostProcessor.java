package io.mateo.dynamicbeans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class FeignClientFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {
    private FeignConfigurationProperties properties;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        properties.getDownstreamServices().getRest().forEach((beanName, props) -> makeClient(beanName, props, registry));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // no-op
    }

    @Override
    public void setEnvironment(@Nullable Environment environment) {
        bindProperties(environment);
    }

    private void bindProperties(Environment environment) {
        this.properties = Binder.get(environment)
                .bind("app", FeignConfigurationProperties.class)
                .orElseThrow(IllegalStateException::new);
    }

    private void makeClient(String beanName, FeignClientProperties props, BeanDefinitionRegistry registry) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(props.getApiType());
        beanDefinition.setInstanceSupplier(() -> FeignClientFactory.create(props));
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
