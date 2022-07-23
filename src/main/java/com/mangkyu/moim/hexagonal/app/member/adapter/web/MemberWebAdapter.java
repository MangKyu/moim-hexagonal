package com.mangkyu.moim.hexagonal.app.member.adapter.web;

import com.mangkyu.moim.hexagonal.app.member.domain.port.in.MemberUseCase;
import com.mangkyu.moim.hexagonal.app.member.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
class MemberWebAdapter {

    private final MemberUseCase memberUseCase;

    @PostMapping("/api/members")
    public ResponseEntity<AddMemberResponse> addMember(@RequestBody @Valid final AddMemberRequest addMemberRequest) {
        final Member member = memberUseCase.addMember(MemberConverter.INSTANCE.toMember(addMemberRequest));

        return ResponseEntity.created(URI.create("/api/members/" + member.getId()))
                .body(MemberConverter.INSTANCE.toAddMemberResponse(member));
    }

}
