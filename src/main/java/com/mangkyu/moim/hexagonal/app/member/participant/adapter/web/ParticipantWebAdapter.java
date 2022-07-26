package com.mangkyu.moim.hexagonal.app.member.participant.adapter.web;

import com.mangkyu.moim.hexagonal.app.member.participant.converter.ParticipantConverter;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.port.in.ParticipantUseCase;
import com.mangkyu.moim.hexagonal.config.security.authorize.HasPathPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
class ParticipantWebAdapter {

    private final ParticipantUseCase participantUseCase;

    @PostMapping("/api/members/participants")
    public ResponseEntity<AddParticipantResponse> addParticipant(@RequestBody @Valid final AddParticipantRequest addParticipantRequest) {
        final Participant participant = participantUseCase.addParticipant(ParticipantConverter.INSTANCE.toParticipant(addParticipantRequest));

        return ResponseEntity.created(URI.create("/api/members/participants/" + participant.getId()))
                .body(ParticipantConverter.INSTANCE.toAddParticipantResponse(participant));
    }

    @HasPathPermission
    @PatchMapping("/api/members/participants/{id}")
    public ResponseEntity<AddParticipantResponse> modifyParticipant(
            @PathVariable final Long id,
            @RequestBody @Valid final ModifyParticipantRequest request) {

        final Participant participant = participantUseCase.modifyParticipant(id, ParticipantConverter.INSTANCE.toParticipant(request));
        return ResponseEntity.ok(ParticipantConverter.INSTANCE.toAddParticipantResponse(participant));
    }

    @HasPathPermission
    @PostMapping("/api/members/participants/{id}/role")
    public ResponseEntity<AddParticipantResponse> addParticipantRole(
            @PathVariable final Long id,
            @RequestBody @Valid final AddParticipantRoleRequest request) {
        final Participant participant = participantUseCase.addRole(id, ParticipantConverter.INSTANCE.toParticipant(request));

        return ResponseEntity.created(URI.create("/api/members/participants/" + participant.getId() + "/role"))
                .body(ParticipantConverter.INSTANCE.toAddParticipantResponse(participant));
    }

}
