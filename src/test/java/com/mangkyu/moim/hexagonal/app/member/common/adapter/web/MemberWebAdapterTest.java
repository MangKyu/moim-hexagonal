package com.mangkyu.moim.hexagonal.app.member.common.adapter.web;

import com.google.gson.Gson;
import com.mangkyu.moim.hexagonal.app.login.domain.in.ParseLoginTokenUseCase;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.ChangePasswordUseCase;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.GetMemberInfoUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.mangkyu.moim.hexagonal.app.login.LoginTestSource.loginTokenClaims;
import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.memberInfo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MemberWebAdapterTest {

    @Autowired
    private MockMvc target;
    @Autowired
    private Gson gson;
    @Autowired
    private ChangePasswordUseCase changePasswordUseCase;
    @Autowired
    private GetMemberInfoUseCase getMemberInfoUseCase;
    @Autowired
    private ParseLoginTokenUseCase tokenUseCase;

    @Test
    void 사용자정보조회성공() throws Exception {
        doReturn(loginTokenClaims())
                .when(tokenUseCase)
                .parseClaims(any());

        doReturn(memberInfo())
                .when(getMemberInfoUseCase)
                .getMemberInfo(anyLong());


        final ResultActions result = target.perform(MockMvcRequestBuilders.get("/api/members/me"));

        result.andExpect(status().isOk());
    }

    @Test
    void 권한추가성공() throws Exception {
        final ModifyMemberPasswordRequest request = modifyMemberPasswordRequest("dkssudgktpdy123!@#");

        doReturn(loginTokenClaims())
                .when(tokenUseCase)
                .parseClaims(any());

        doNothing()
                .when(changePasswordUseCase)
                .changePassword(anyLong(), anyString());


        final ResultActions result = target.perform(MockMvcRequestBuilders.put("/api/members/me/password")
                .content(gson.toJson(request))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @CsvSource({
            "gk13!@", "123123@#", "dkssudgktpdy123", "gkdsgasdg!@"
    })
    @ParameterizedTest
    void 사용자추가실패_잘못된파라미터(final String password) throws Exception {
        final ModifyMemberPasswordRequest request = modifyMemberPasswordRequest(password);

        doReturn(loginTokenClaims())
                .when(tokenUseCase)
                .parseClaims(any());

        doNothing()
                .when(changePasswordUseCase)
                .changePassword(anyLong(), anyString());


        final ResultActions result = target.perform(MockMvcRequestBuilders.put("/api/members/me/password", 1L)
                .content(gson.toJson(request))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    private ModifyMemberPasswordRequest modifyMemberPasswordRequest(final String password) {
        return ModifyMemberPasswordRequest.builder()
                .password(password)
                .build();
    }
}