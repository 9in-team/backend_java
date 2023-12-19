package com.guin.team.acceptance;

import com.guin.team.util.DataCleanUp;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestConstructor;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public abstract class AcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    private DataCleanUp dataCleanUp;

    public void setUp() {
        RestAssured.port = port;
        dataCleanUp.execute();
    }

}
