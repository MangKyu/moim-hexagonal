package com.mangkyu.moim.hexagonal.member.adapter.web;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MemberWebAdapterTest {

    @Autowired
    private MockMvc target;
    @Autowired
    private Gson gson;

    @CsvSource({
            "mangkyu,,dkssudgktpdy123!@#", ",dkssudgktpdy123!@#",
            "mangkyu@naver.com,", "mangkyu@naver.com, gk13!@", "mangkyu@naver.com, 123123@#", "mangkyu@naver.com, dkssudgktpdy123", "mangkyu@naver.com, gkdsgasdg!@"

    })
    @ParameterizedTest
    void 사용자추가실패_잘못된파라미터(final String email, final String password) throws Exception {
        final AddMemberRequest addMemberRequest = AddMemberRequest.builder()
                .email(email)
                .password(password)
                .build();

        final ResultActions result = target.perform(MockMvcRequestBuilders.post("/api/members")
                .content(gson.toJson(addMemberRequest))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void 사용자추가성공() throws Exception {
        final AddMemberRequest addMemberRequest = AddMemberRequest.builder()
                .email("mangkyu@naver.com")
                .password("dkssudgktpdy123!@#")
                .build();

        final ResultActions result = target.perform(MockMvcRequestBuilders.post("/api/members")
                .content(gson.toJson(addMemberRequest))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

}