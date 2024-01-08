package com.guin.team.acceptance;

import com.guin.team.TeamServiceApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@CucumberContextConfiguration
@ComponentScan(basePackages = "com.guin")
@SpringBootTest(classes = TeamServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberAcceptance {
}
