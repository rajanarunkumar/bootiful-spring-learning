package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "com.example.demo.repository.jpa"
//    , repositoryFactoryBeanClass = org.springframework.data.jpa.query.CustomJpaRepositoryFactoryBean.class
)
public class DemoRepositoryConfig {

}
