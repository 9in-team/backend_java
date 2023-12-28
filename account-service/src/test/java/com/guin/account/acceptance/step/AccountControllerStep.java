package com.guin.account.acceptance.step;

import com.guin.account.adapter.in.web.dto.AccountCreateRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class AccountControllerStep {

  public static ExtractableResponse<Response> create(AccountCreateRequest request) {
    return RestAssured
        .given().log().all()
        .body(request)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when().post("/account")
        .then().log().all()
        .extract();
  }
}
