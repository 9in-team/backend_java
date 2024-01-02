package com.guin.team.integration;

import com.guin.team.util.DataCleanUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
public abstract class IntegrationTest {

    @Autowired
    private DataCleanUp dataCleanUp;

    public void setup() {
        dataCleanUp.execute();
    }
}
