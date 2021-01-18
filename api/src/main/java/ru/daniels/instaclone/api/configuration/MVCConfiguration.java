package ru.daniels.instaclone.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MVCConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("media/" + Constants.IMAGES_FOLDER + "**")
                .addResourceLocations("classpath:/" + Constants.IMAGES_FOLDER)
                .setCachePeriod(31556926);
    }




}
