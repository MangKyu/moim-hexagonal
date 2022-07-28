package com.mangkyu.moim.hexagonal.app.member.common.adapter.web;

import com.mangkyu.moim.hexagonal.app.member.common.domain.in.MemberUseCase;
import com.mangkyu.moim.hexagonal.config.security.authorize.HasPathPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class MemberWebAdapter {

    private final MemberUseCase memberUseCase;

    @HasPathPermission
    @PutMapping("/api/members/{id}/password")
    public ResponseEntity<Void> changePassword(
            @PathVariable final Long id,
            @RequestBody @Valid final ModifyMemberPasswordRequest request) {

        memberUseCase.changePassword(id, request.getPassword());

        return ResponseEntity.ok()
                .build();
    }

}
