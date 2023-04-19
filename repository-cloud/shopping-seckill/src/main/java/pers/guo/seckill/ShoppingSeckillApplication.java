package pers.guo.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShoppingSeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingSeckillApplication.class, args);
    }

}
