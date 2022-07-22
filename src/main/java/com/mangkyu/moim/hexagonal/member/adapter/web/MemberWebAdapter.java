package com.mangkyu.moim.hexagonal.member.adapter.web;

import com.mangkyu.moim.hexagonal.member.application.MemberService;
import com.mangkyu.moim.hexagonal.member.domain.port.in.MemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class MemberWebAdapter {

    private final MemberUseCase memberUseCase;

    @PostMapping("/api/members")
    public ResponseEntity<Void> addMember(@RequestBody @Valid final AddMemberRequest addMemberRequest) {
        memberUseCase.addMember(addMemberRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

}
