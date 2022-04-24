package com.mehtab.backendapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The type Backend api application.
 * @author mehtab singh
 */
@SpringBootApplication
@EnableFeignClients
public class BackendApiApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BackendApiApplication.class, args);
    }

}
