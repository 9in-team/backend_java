package com.guin.account.acceptance;

import com.guin.account.port.out.AccountPort;
import io.restassured.RestAssured;
import java.util.Optional;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("local")
public abstract class AcceptanceTest {

  @LocalServerPort
  int port;

  @Autowired
  AccountPort accountPort;

  public void setUp() {
    RestAssured.port = port;
    Optional<UserRepresentation> testUser = accountPort.search("test_username");
    if (testUser.isPresent()) {
      accountPort.delete(testUser.get().getId());
    }
  }

}
