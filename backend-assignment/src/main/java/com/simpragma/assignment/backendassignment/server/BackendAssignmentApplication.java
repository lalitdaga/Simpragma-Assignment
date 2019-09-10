package com.simpragma.assignment.backendassignment.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.simpragma.assignment.backendassignment"})
@EnableJpaRepositories(basePackages = "com.simpragma.assignment.backendassignment.repository")
@EntityScan(basePackages = {"com.simpragma.assignment.backendassignment.entity"})
public class BackendAssignmentApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendAssignmentApplication.class, args);
  }

}
