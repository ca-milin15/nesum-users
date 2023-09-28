package com.nesum.nesum.shared.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfigurerAdapterCustom implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("swagger-ui.html")
       .addResourceLocations("classpath:/META-INF/resources/");

       registry.addResourceHandler("/webjars/**")
       .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

}
