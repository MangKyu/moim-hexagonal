package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web;

import com.google.gson.Gson;
import com.mangkyu.moim.hexagonal.app.login.domain.in.ParseLoginTokenUseCase;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.in.OrganizerUseCase;
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
import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerTestSource.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class OrganizerWebAdapterTest {

    @Autowired
    private MockMvc target;
    @Autowired
    private Gson gson;
    @Autowired
    private OrganizerUseCase organizerUseCase;
    @Autowired
    private ParseLoginTokenUseCase tokenUseCase;

    @Test
    void 권한추가성공() throws Exception {
        final AddOrganizerRoleRequest request = addOrganizerRoleRequest();

        doReturn(loginTokenClaims())
                .when(tokenUseCase)
                .parseClaims(any());

        doReturn(organizer())
                .when(organizerUseCase)
                .addRole(anyLong(), any(Organizer.class));


        final ResultActions result = target.perform(MockMvcRequestBuilders.post("/api/members/organizers/{id}/role", 1L)
                .content(gson.toJson(request))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

    @Test
    void 사용자정보수정성공() throws Exception {
        final ModifyOrganizerRequest request = modifyOrganizerRequest();

        doReturn(loginTokenClaims())
                .when(tokenUseCase)
                .parseClaims(any());

        doReturn(organizer())
                .when(organizerUseCase)
                .addOrganizer(any(Organizer.class));

        final ResultActions result = target.perform(
                MockMvcRequestBuilders.patch("/api/members/organizers/{id}", 1L)
                        .header("Authorization", "Bearer " + "token")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    void 자신정보수정성공() throws Exception {
        final ModifyOrganizerRequest request = modifyOrganizerRequest();

        doReturn(loginTokenClaims())
                .when(tokenUseCase)
                .parseClaims(any());

        doReturn(organizer())
                .when(organizerUseCase)
                .addOrganizer(any(Organizer.class));

        final ResultActions result = target.perform(
                MockMvcRequestBuilders.patch("/api/members/organizers/me")
                        .header("Authorization", "Bearer " + "token")
                        .content(gson.toJson(request))
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @CsvSource({
            "mangkyu,dkssudgktpdy123!@#", ",dkssudgktpdy123!@#",
            "mangkyu@naver.com,", "mangkyu@naver.com, gk13!@", "mangkyu@naver.com, 123123@#", "mangkyu@naver.com, dkssudgktpdy123", "mangkyu@naver.com, gkdsgasdg!@"
    })
    @ParameterizedTest
    void 사용자추가실패_잘못된파라미터(final String email, final String password) throws Exception {
        final AddOrganizerRequest addOrganizerRequest = addOrganizerRequest(email, password);

        final ResultActions result = target.perform(MockMvcRequestBuilders.post("/api/members/organizers")
                .content(replaceBirth(addOrganizerRequest))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void 사용자추가성공() throws Exception {
        final AddOrganizerRequest addOrganizerRequest = addOrganizerRequest();

        doReturn(organizer())
                .when(organizerUseCase)
                .addOrganizer(any(Organizer.class));


        final ResultActions result = target.perform(MockMvcRequestBuilders.post("/api/members/organizers")
                .content(replaceBirth(addOrganizerRequest))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

    private String replaceBirth(final AddOrganizerRequest addOrganizerRequest) {
        final String json = gson.toJson(addOrganizerRequest);
        return json.replace("{\"year\":1994,\"month\":12,\"day\":26}", "19941226");
    }

}