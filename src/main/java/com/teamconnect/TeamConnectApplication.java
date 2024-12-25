package com.teamconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TeamConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamConnectApplication.class, args);
    }

}
