package com.mangkyu.moim.hexagonal.app.member.common.adapter.web;

import com.mangkyu.moim.hexagonal.app.login.domain.LoginMember;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.MemberUseCase;
import com.mangkyu.moim.hexagonal.config.security.authenticate.Authenticated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class MemberWebAdapter {

    private final MemberUseCase memberUseCase;

    @PutMapping("/api/members/me/password")
    public ResponseEntity<Void> changePassword(
            @Authenticated final LoginMember loginMember,
            @RequestBody @Valid final ModifyMemberPasswordRequest request) {

        memberUseCase.changePassword(loginMember.getId(), request.getPassword());

        return ResponseEntity.ok()
                .build();
    }

}
