package com.guin.account.application;

import com.guin.account.adapter.in.web.dto.AccountCreateRequest;
import com.guin.account.adapter.out.persistence.keyCloakFacade;
import com.guin.account.port.in.AccountUseCase;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountUseCase {

  private final keyCloakFacade keycloak;

  @Override
  public String create(AccountCreateRequest accountCreateRequest) {
    UserRepresentation user = new UserRepresentation();
    user.setEnabled(true);
    user.setUsername(accountCreateRequest.username());
    user.setFirstName(accountCreateRequest.firstName());
    user.setLastName(accountCreateRequest.lastName());
    user.setEmail(accountCreateRequest.email());
    user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

    return keycloak.create(user);
  }

  @Override
  public Optional<UserRepresentation> search(final String userName) {
    return keycloak.search(userName);
  }
}
