package com.mangkyu.moim.hexagonal.app.login;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.Map;

public class LoginRestAssuredTestSource {

    public static ExtractableResponse<Response> 로그인(final String loginId, final String password) {
        return RestAssured.given().log().all()
                .body(loginParams(loginId, password))
                .contentType(ContentType.JSON)
                .when().post("/api/login")
                .then().log().all()
                .extract();
    }

    public static String 로그인토큰(final String email, final String password) {
        return RestAssured.given().log().all()
                .body(loginParams(email, password))
                .contentType(ContentType.JSON)
                .when().post("/api/login")
                .then().log().all()
                .extract()
                .path("accessToken");
    }

    private static Map<String, Object> loginParams(final String loginId, final String password) {
        return Map.of("loginId", loginId, "password", password);
    }

}
