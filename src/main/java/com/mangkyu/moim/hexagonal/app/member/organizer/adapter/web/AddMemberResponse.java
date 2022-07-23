package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class AddMemberResponse {

    private final Long id;
    private final String email;

}
