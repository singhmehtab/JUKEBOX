package com.mehtab.backendapi.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * The type Interceptor config.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    private AuthInterceptor authInterceptor;

    /**
     * Instantiates a new Interceptor config.
     *
     * @param authInterceptor the auth interceptor
     */
    @Autowired
    public InterceptorConfig(AuthInterceptor authInterceptor){
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }
}

