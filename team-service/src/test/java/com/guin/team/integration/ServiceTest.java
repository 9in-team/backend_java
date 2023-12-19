package com.guin.team.integration;

import com.guin.team.util.DataCleanUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public abstract class ServiceTest {

    @Autowired
    private DataCleanUp dataCleanUp;

    public void setup() {
        dataCleanUp.execute();
    }
}
