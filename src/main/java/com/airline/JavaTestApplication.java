package com.airline;

import com.airline.ws.WebServiceClientHelper;
import jersey.repackaged.com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootApplication
public class JavaTestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JavaTestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExecutorService executorService(@Value("${thread.pool.size}") Integer threadPoolSize) {
        return Executors.newFixedThreadPool(threadPoolSize);
    }

    @Bean
    public WebServiceClientHelper webServiceClientHelper() {
        return new WebServiceClientHelper();
    }

    @Bean
    public ScheduledExecutorService scheduler () {
        return Executors.newScheduledThreadPool(1,
                new ThreadFactoryBuilder()
                        .setDaemon(true)
                        .setNameFormat("failAfter-%d")
                        .build());
    }

    @Component
    public class JerseyConfig extends ResourceConfig {
        public JerseyConfig() {
            register(Endpoint.class);
        }
    }
}
