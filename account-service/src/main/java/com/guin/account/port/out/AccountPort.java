package com.guin.account.port.out;

import java.util.Optional;
import org.keycloak.representations.idm.UserRepresentation;

public interface AccountPort {

  String create(UserRepresentation userRepresentation);

  Optional<UserRepresentation> search(String userName);

  void delete(String userId);
}
