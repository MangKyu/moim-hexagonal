package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web;

import com.mangkyu.moim.hexagonal.app.member.organizer.converter.OrganizerConverter;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.in.OrganizerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
class OrganizerWebAdapter {

    private final OrganizerUseCase organizerUseCase;

    @PostMapping("/api/members/organizers")
    public ResponseEntity<AddOrganizerResponse> addOrganizer(@RequestBody @Valid final AddOrganizerRequest addOrganizerRequest) {
        final Organizer organizer = organizerUseCase.addOrganizer(OrganizerConverter.INSTANCE.toOrganizer(addOrganizerRequest));

        return ResponseEntity.created(URI.create("/api/members/organizers/" + organizer.getId()))
                .body(OrganizerConverter.INSTANCE.toAddOrganizerResponse(organizer));
    }

    @PatchMapping("/api/members/organizers/{id}")
    public ResponseEntity<AddOrganizerResponse> modifyOrganizer(
            @PathVariable final Long id,
            @RequestBody @Valid final ModifyOrganizerRequest request) {

        final Organizer organizer = organizerUseCase.modifyOrganizer(id, OrganizerConverter.INSTANCE.toOrganizer(request));
        return ResponseEntity.ok(OrganizerConverter.INSTANCE.toAddOrganizerResponse(organizer));
    }

}
