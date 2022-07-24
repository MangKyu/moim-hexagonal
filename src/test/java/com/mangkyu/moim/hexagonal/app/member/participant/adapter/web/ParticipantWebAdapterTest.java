package com.mangkyu.moim.hexagonal.app.member.participant.adapter.web;

import com.google.gson.Gson;
import com.mangkyu.moim.hexagonal.app.login.domain.in.ParseLoginTokenUseCase;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.port.in.ParticipantUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.mangkyu.moim.hexagonal.app.member.participant.ParticipantTestSource.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ParticipantWebAdapterTest {

    @Autowired
    private MockMvc target;
    @Autowired
    private Gson gson;
    @Autowired
    private ParticipantUseCase participantUseCase;
    @Autowired
    private ParseLoginTokenUseCase tokenUseCase;

    @Test
    void 사용자정보수정성공() throws Exception {
        final ModifyParticipantRequest request = modifyParticipantRequest();
        doReturn("token")
                .when(tokenUseCase)
                .parseEmail(any());

        doReturn(participant())
                .when(participantUseCase)
                .addParticipant(any(Participant.class));

        final ResultActions result = target.perform(
                MockMvcRequestBuilders.patch("/api/members/participants/{id}", 1L)
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
        final AddParticipantRequest addParticipantRequest = addParticipantRequest(email, password);

        final ResultActions result = target.perform(MockMvcRequestBuilders.post("/api/members/participants")
                .content(replaceBirth(addParticipantRequest))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void 사용자추가성공() throws Exception {
        final AddParticipantRequest addParticipantRequest = addParticipantRequest();

        doReturn(participant())
                .when(participantUseCase)
                .addParticipant(any(Participant.class));


        final ResultActions result = target.perform(MockMvcRequestBuilders.post("/api/members/participants")
                .content(replaceBirth(addParticipantRequest))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

    private String replaceBirth(final AddParticipantRequest addParticipantRequest) {
        final String json = gson.toJson(addParticipantRequest);
        return json.replace("{\"year\":1994,\"month\":12,\"day\":26}", "19941226");
    }

}