package com.jbhunt.infrastructure.header360.configuration;

import com.jbhunt.infrastructure.header360.PlatformHeader360Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PlatformHeader360Application.class);
    }
}
