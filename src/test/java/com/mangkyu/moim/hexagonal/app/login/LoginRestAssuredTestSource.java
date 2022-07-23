package com.mangkyu.moim.hexagonal.app.login;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.Map;

public class LoginRestAssuredTestSource {

    public static ExtractableResponse<Response> 구성원조회(final Long 구성원, final String token) {
        return RestAssured.given().log().all()
                .header("Authorization", "Bearer " + token)
                .when().get("/api/members/{id}", 구성원)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인(final String email, final String password) {
        return RestAssured.given().log().all()
                .body(loginParams(email, password))
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
                .path("token");
    }

    private static Map<String, Object> loginParams(final String email, final String password) {
        return Map.of("email", email, "password", password);
    }

}
