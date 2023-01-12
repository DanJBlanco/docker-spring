package org.dblanco.springcloud.msvc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsvcGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcGatewayApplication.class, args);
    }

}
