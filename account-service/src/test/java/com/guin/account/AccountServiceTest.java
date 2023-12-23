package com.guin.account;


import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

class AccountServiceTest extends AcceptanceTest {

  @DisplayName("회원을 생성한다.")
  @Test
  void createUser() {
    AccountService accountService = new AccountService();
    accountService.create();

    Keycloak keycloak = KeycloakBuilder.builder() //
        .serverUrl("http://localhost:8082") //
        .realm("guin") //
        .grantType("client_credentials") //
        .clientId("guin-service") //
        .clientSecret("JnZTAr8GlAhW9YIkbPWYtIb9ppdx36E4") //
        .build();

    List<UserRepresentation> search = keycloak.realm("guin").users().search("tester3");
    assertThat(search).hasSize(1);
  }


  private class AccountService {
    public void create(){
      Keycloak keycloak = KeycloakBuilder.builder() //
          .serverUrl("http://localhost:8082") //
          .realm("guin") //
          .grantType("client_credentials") //
          .clientId("guin-service") //
          .clientSecret("JnZTAr8GlAhW9YIkbPWYtIb9ppdx36E4") //
          .build();

      UserRepresentation user = new UserRepresentation();
      user.setEnabled(true);
      user.setUsername("tester3");
      user.setFirstName("First");
      user.setLastName("Last");
      user.setEmail("tom+tester3@tdlabs.local");
      user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

      // Get realm
      RealmResource realmResource = keycloak.realm("guin");
      UsersResource usersRessource = realmResource.users();

      // Create user (requires manage-users role)
      Response response = usersRessource.create(user);
      System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
      System.out.println(response.getLocation());
      String userId = CreatedResponseUtil.getCreatedId(response);

      System.out.println("userId = " + userId);
    }
  }
}