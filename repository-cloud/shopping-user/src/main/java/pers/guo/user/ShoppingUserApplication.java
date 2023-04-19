package pers.guo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShoppingUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingUserApplication.class, args);
    }

}
