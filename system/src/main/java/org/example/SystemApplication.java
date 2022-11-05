package org.example;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "org.example")
public class SystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(SystemApplication.class, args);
    }

}
