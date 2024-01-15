package br.com.ecommerce.infrastructure.common;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FactoryBeanConfig {
    @Bean
    FactoryBean<Object> simpleFactoryBean() {
        var factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(SimpleFactoryBean.class);
        return factoryBean;
    }
}
