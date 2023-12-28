package com.guin.account.adapter.out.persistence;

import com.guin.account.port.out.AccountPort;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.Response;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class keyCloakFacade implements AccountPort {

  @Value("${keycloak.auth-server-url}")
  private String keycloakAuthServerUrl;

  @Value("${keycloak.realm}")
  private String keycloakRealm;

  @Value("${keycloak.client-id}")
  private String keycloakClientId;

  @Value("${keycloak.client-secret}")
  private String keycloakClientSecret;

  public Keycloak getKeycloak() {
    return KeycloakBuilder.builder()
        .serverUrl(keycloakAuthServerUrl)
        .realm(keycloakRealm)
        .grantType("client_credentials")
        .clientId(keycloakClientId)
        .clientSecret(keycloakClientSecret)
        .build();
  }

  @Override
  public String create(final UserRepresentation userRepresentation) {
    final UsersResource usersResource = getKeycloak().realm(keycloakRealm).users();
    final Response response = usersResource.create(userRepresentation);
    System.out.printf("Response: %s %s%n", response.getStatus(), response.getStatusInfo());
    System.out.println(response.getLocation());
    return CreatedResponseUtil.getCreatedId(response);
  }

  @Override
  public Optional<UserRepresentation> search(final String userName) {
    List<UserRepresentation> users = getKeycloak().realm(keycloakRealm).users().search(userName);
    return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
  }


  @Override
  public void delete(String userId) {
    Keycloak keycloak = getKeycloak();
    keycloak.realm(keycloakRealm).users().delete(userId);
  }

}
