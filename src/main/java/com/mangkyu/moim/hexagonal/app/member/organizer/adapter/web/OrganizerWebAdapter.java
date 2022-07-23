package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web;

import com.mangkyu.moim.hexagonal.app.member.organizer.converter.OrganizerConverter;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.in.OrganizerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
class OrganizerWebAdapter {

    private final OrganizerUseCase organizerUseCase;

    @PostMapping("/api/members/organizers")
    public ResponseEntity<AddOrganizerResponse> addMember(@RequestBody @Valid final AddOrganizerRequest addOrganizerRequest) {
        final Organizer organizer = organizerUseCase.addOrganizer(OrganizerConverter.INSTANCE.toOrganizer(addOrganizerRequest));

        return ResponseEntity.created(URI.create("/api/members/organizers/" + organizer.getId()))
                .body(OrganizerConverter.INSTANCE.toAddOrganizerResponse(organizer));
    }

}
