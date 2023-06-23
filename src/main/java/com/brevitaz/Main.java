package com.brevitaz;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info =@Info(title = "Student CRUD API" , version =  "2.0.3" ,description = "Students CRUD service"))
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
