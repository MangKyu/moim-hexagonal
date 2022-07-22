package com.mangkyu.moim.hexagonal.member.adapter.web;

import com.mangkyu.moim.hexagonal.member.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.member.domain.Member;
import com.mangkyu.moim.hexagonal.member.domain.port.in.MemberUseCase;
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
