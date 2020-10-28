package com.mint.net.rest;     

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.h2.server.web.WebServlet;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class WebConfiguration {
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "custom.rest.connection")
    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory()
    {
        return new HttpComponentsClientHttpRequestFactory();
    }

    @Bean
    public RestTemplate customRestTemplate()
    {
        return new RestTemplate(customHttpRequestFactory());
    }



}
