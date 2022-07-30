package com.mangkyu.moim.hexagonal.app.member.common.adapter.web;

import com.mangkyu.moim.hexagonal.app.login.domain.LoginMember;
import com.mangkyu.moim.hexagonal.app.member.common.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.ChangePasswordUseCase;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.GetMemberInfoUseCase;
import com.mangkyu.moim.hexagonal.config.security.authenticate.Authenticated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class MemberWebAdapter {

    private final ChangePasswordUseCase changePasswordUseCase;
    private final GetMemberInfoUseCase getMemberInfoUseCase;

    @PutMapping("/api/members/me/password")
    public ResponseEntity<Void> changePassword(
            @Authenticated final LoginMember loginMember,
            @RequestBody @Valid final ModifyMemberPasswordRequest request) {

        changePasswordUseCase.changePassword(loginMember.getId(), request.getPassword());

        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/api/members/me")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(@Authenticated final LoginMember loginMember) {
        final MemberInfo memberInfo = getMemberInfoUseCase.getMemberInfo(loginMember.getId());
        return ResponseEntity.ok(MemberConverter.INSTANCE.toMemberInfoResponse(memberInfo));
    }

}
