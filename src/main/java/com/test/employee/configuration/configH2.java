package com.test.employee.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class configH2 {

@Bean
ServletRegistrationBean h2servletRegistration(){
    ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
    registrationBean.addUrlMappings("/console/*");
    return registrationBean;
    //http://localhost:8080/console/
}

}
