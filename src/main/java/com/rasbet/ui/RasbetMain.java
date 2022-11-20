package com.rasbet.ui;

import com.rasbet.model.RasBetFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RasbetMain {

    public static void main(String[] args) {
        RasBetFacade.emailAuthenticatedUser = "a@gmail.com";
        SpringApplication.run(RasbetMain.class, args);
    }

}
