package com.mangkyu.moim.hexagonal.app.member.organizer;

import com.mangkyu.moim.hexagonal.acceptance.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerRestAssuredTestSource.주최자추가;
import static org.assertj.core.api.Assertions.assertThat;

@AcceptanceTest
class OrganizerAcceptanceTest {

    @Test
    void 주최자가입성공() {
        final ExtractableResponse<Response> 참여자추가결과 = 주최자추가("mangkyu1226", "dkssudgktpdy123!@#");
        가입성공(참여자추가결과);
    }

    @Test
    void 주최자중복되는아이디가입실패() {
        주최자추가("mangkyu1226", "dkssudgktpdy123!@#");

        final ExtractableResponse<Response> 참여자추가 = 주최자추가("mangkyu1226", "dkssudgktpdy123!@#");
        가입실패(참여자추가);
    }

    private void 가입성공(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private void 가입실패(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}
