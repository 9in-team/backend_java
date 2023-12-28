package com.guin.account.port.in;

import com.guin.account.adapter.in.web.dto.AccountCreateRequest;
import java.util.Optional;
import org.keycloak.representations.idm.UserRepresentation;

public interface AccountUseCase {

  String create(AccountCreateRequest accountCreateRequest);

  Optional<UserRepresentation> search(String userName);
}
