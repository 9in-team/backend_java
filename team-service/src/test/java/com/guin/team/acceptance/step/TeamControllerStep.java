package com.guin.team.acceptance.step;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class TeamControllerStep {

    public static ExtractableResponse<Response> create(TeamCreateRequest request) {
        return RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/team")
                .then().log().all()
                .extract();
    }

}
