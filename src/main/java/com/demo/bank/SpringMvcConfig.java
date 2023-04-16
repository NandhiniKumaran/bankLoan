package com.demo.bank;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/accounts/new").setViewName("newCredit");
        registry.addViewController("/accounts").setViewName("credit");

    }
}
