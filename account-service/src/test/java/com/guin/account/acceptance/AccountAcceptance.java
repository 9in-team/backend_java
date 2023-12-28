package com.guin.account.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import com.guin.account.acceptance.step.AccountControllerStep;
import com.guin.account.adapter.in.web.dto.AccountCreateRequest;
import com.guin.account.port.in.AccountUseCase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class AccountAcceptance extends AcceptanceTest {

  private final AccountUseCase accountUseCase;
  private AccountCreateRequest accountCreateRequest;

  public AccountAcceptance(AccountUseCase accountUseCase) {
    this.accountUseCase = accountUseCase;
  }

  @BeforeEach
  public void setUp() {
    super.setUp();
    accountCreateRequest = new AccountCreateRequest(
        "test_username",
        "test_firstName",
        "test_lastName",
        "test@naver.com");
  }

  @DisplayName("회원을 생성한다.")
  @Test
  void createUser() {
    //given
    JsonPath response = AccountControllerStep.create(accountCreateRequest).jsonPath();

    accountUseCase.search("test_username").isPresent();
    //when
    accountUseCase.create(accountCreateRequest);
    //then
    assertThat(accountPort.search("test_username").isPresent()).isTrue();
  }

  @DisplayName("파라미터에 필수값이 누락될 경우 에러를 반환한다.")
  @Test
  void invalidParameter() {
    AccountCreateRequest errorRequest = new AccountCreateRequest(null, null, null, null);

    ExtractableResponse<Response> response = AccountControllerStep.create(errorRequest);

    assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
  }
}
