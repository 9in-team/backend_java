package com.guin.account.intergration;


import static org.assertj.core.api.Assertions.assertThat;

import com.guin.account.adapter.in.web.dto.AccountCreateRequest;
import com.guin.account.application.AccountService;
import com.guin.account.port.out.AccountPort;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("local")
class AccountServiceTest {

  @Autowired
  AccountService accountService;
  @Autowired
  AccountPort accountPort;

  @BeforeEach
  public void setUp() {
    Optional<UserRepresentation> test_username = accountService.search("test_username");
    if (test_username.isPresent()) {
      accountPort.delete(test_username.get().getId());
    }
  }

  @DisplayName("회원을 생성한다.")
  @Test
  void createUser() {
    //given
    AccountCreateRequest accountCreateRequest = new AccountCreateRequest(
        "test_username",
        "test_firstName",
        "test_lastName",
        "test@naver.com");

    //when
    accountService.create(accountCreateRequest);
    Optional<UserRepresentation> searchResult = accountService.search("test_username");

    //
    assertThat(searchResult.isPresent()).isTrue();
    UserRepresentation userRepresentation = searchResult.get();
    assertThat(userRepresentation.getUsername()).isEqualTo("test_username");
    assertThat(userRepresentation.getFirstName()).isEqualTo("test_firstName");
    assertThat(userRepresentation.getLastName()).isEqualTo("test_lastName");
  }


}

