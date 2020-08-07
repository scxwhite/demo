package com.thrcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author scx
 */
@SpringBootApplication(scanBasePackages = "com.thrcat")
@ImportResource(locations = {"classpath:/dubbo/provider.xml", "classpath:/dubbo/consumer.xml"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
