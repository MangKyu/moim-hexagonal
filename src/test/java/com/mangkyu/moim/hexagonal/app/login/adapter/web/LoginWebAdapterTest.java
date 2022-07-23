package com.mangkyu.moim.hexagonal.app.login.adapter.web;

import com.google.gson.Gson;
import com.mangkyu.moim.hexagonal.app.login.domain.Login;
import com.mangkyu.moim.hexagonal.app.login.domain.in.LoginUseCase;
import com.mangkyu.moim.hexagonal.app.member.adapter.web.AddMemberRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.mangkyu.moim.hexagonal.app.login.LoginTestSource.loginRequest;
import static com.mangkyu.moim.hexagonal.app.member.MemberTestSource.addMemberRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class LoginWebAdapterTest {

    @Autowired
    private MockMvc target;
    @Autowired
    private Gson gson;
    @Autowired
    private LoginUseCase loginUseCase;

    @CsvSource({
            "mangkyu,,dkssudgktpdy123!@#", ",dkssudgktpdy123!@#",
            "mangkyu@naver.com,", "mangkyu@naver.com, gk13!@", "mangkyu@naver.com, 123123@#", "mangkyu@naver.com, dkssudgktpdy123", "mangkyu@naver.com, gkdsgasdg!@"
    })
    @ParameterizedTest
    void 로그인실패_잘못된아이디패스워드(final String email, final String password) throws Exception {
        final LoginRequest loginRequest = loginRequest(email, password);

        final ResultActions result = target.perform(MockMvcRequestBuilders.post("/api/members")
                .content(gson.toJson(loginRequest))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void 로그인성공() throws Exception {
        doReturn("token")
                .when(loginUseCase)
                .login(any(Login.class));


        final ResultActions result = target.perform(MockMvcRequestBuilders.post("/api/login")
                .content(gson.toJson(loginRequest()))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

}