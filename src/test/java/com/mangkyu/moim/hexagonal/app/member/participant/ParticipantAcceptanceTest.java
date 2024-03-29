package com.mangkyu.moim.hexagonal.app.member.participant;

import com.mangkyu.moim.hexagonal.acceptance.AcceptanceTest;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static com.mangkyu.moim.hexagonal.app.login.LoginRestAssuredTestSource.로그인토큰;
import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerRestAssuredTestSource.주최자역할추가;
import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerRestAssuredTestSource.주최자추가;
import static com.mangkyu.moim.hexagonal.app.member.participant.ParticipantRestAssuredTestSource.참여자역할추가;
import static com.mangkyu.moim.hexagonal.app.member.participant.ParticipantRestAssuredTestSource.참여자추가;
import static org.assertj.core.api.Assertions.assertThat;

@AcceptanceTest
class ParticipantAcceptanceTest {

    @Test
    void 참여자중복되는아이디가입실패() {
        참여자추가("mangkyu1226", "dkssudgktpdy123!@#");

        final ExtractableResponse<Response> 참여자추가 = 참여자추가("mangkyu1226", "dkssudgktpdy123!@#");

        요청실패_잘못된요청(참여자추가);
    }

    @Test
    void 참여자가입성공() {
        final ExtractableResponse<Response> 참여자추가결과 = 참여자추가("mangkyu1226", "dkssudgktpdy123!@#");

        가입성공(참여자추가결과);
    }

    @Test
    void 주최자정보변경실패_본인이아님() {
        참여자추가("temp1226", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final String 임시참여자토큰 = 로그인토큰("temp1226", "dkssudgktpdy123!@#");

        final Long 참여자 = 참여자추가("mangkyu1226", "dkssudgktpdy123!@#").jsonPath().getLong("id");

        final ExtractableResponse<Response> 요청결과 = 참여자정보수정(참여자, 임시참여자토큰);

        요청실패_권한없음(요청결과);
    }

    @Test
    void 참여자정보변경() {
        final Long 참여자 = 참여자추가("mangkyu1226", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final String 토큰 = 로그인토큰("mangkyu1226", "dkssudgktpdy123!@#");

        final ExtractableResponse<Response> 참여자추가결과 = 참여자정보수정(참여자, 토큰);

        수정성공(참여자추가결과);
    }

    @Test
    void 참여자자신정보변경() {
        참여자추가("mangkyu1226", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final String 토큰 = 로그인토큰("mangkyu1226", "dkssudgktpdy123!@#");

        final ExtractableResponse<Response> 참여자추가결과 = 참여자자신정보수정(토큰);

        수정성공(참여자추가결과);
    }

    @Test
    void 참여자권한추가실패_중복권한() {
        final Long 참여자 = 참여자추가("mangkyu1226", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final String 토큰 = 로그인토큰("mangkyu1226", "dkssudgktpdy123!@#");

        final ExtractableResponse<Response> 참여자추가결과 = 참여자역할추가(참여자, 토큰);

        요청실패_잘못된요청(참여자추가결과);
    }

    @Test
    void 참여자권한추가실패_본인이아님() {
        주최자추가("temp1226", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final String 토큰 = 로그인토큰("temp1226", "dkssudgktpdy123!@#");

        final Long 참여자 = 참여자추가("mangkyu1226", "dkssudgktpdy123!@#").jsonPath().getLong("id");

        final ExtractableResponse<Response> 결과 = 주최자역할추가(참여자, 토큰);

        요청실패_권한없음(결과);
    }

    @Test
    void 참여자권한추가성공() {
        final Long 주최자 = 주최자추가("mangkyu1226", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final String 토큰 = 로그인토큰("mangkyu1226", "dkssudgktpdy123!@#");

        final ExtractableResponse<Response> 참여자추가결과 = 참여자역할추가(주최자, 토큰);

        참여자권한추가성공(참여자추가결과);
    }

    private void 참여자권한추가성공(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.jsonPath().getString("roles")).contains(MemberRole.ROLE_PARTICIPANT.name(), MemberRole.ROLE_ORGANIZER.name());
    }

    private ExtractableResponse<Response> 참여자정보수정(final Long id, final String token) {
        return RestAssured.given().log().all()
                .auth().oauth2(token)
                .body(modifyParticipantParam())
                .contentType(ContentType.JSON)
                .when().patch("/api/members/participants/{id}", id)
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> 참여자자신정보수정(final String token) {
        return RestAssured.given().log().all()
                .auth().oauth2(token)
                .body(modifyParticipantParam())
                .contentType(ContentType.JSON)
                .when().patch("/api/members/participants/me")
                .then().log().all()
                .extract();
    }

    private Map<String, Object> modifyParticipantParam() {
        return Map.of("name", "newName", "belongs", "newBelongs");
    }

    private void 가입성공(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private void 수정성공(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private void 요청실패_잘못된요청(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private void 요청실패_권한없음(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

}
