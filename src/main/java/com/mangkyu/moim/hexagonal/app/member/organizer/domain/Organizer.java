package com.mangkyu.moim.hexagonal.app.member.organizer.domain;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Delegate;

@Getter
@Builder
@AllArgsConstructor
public class Organizer {

    private Long id;
    private String belongs;

    @Delegate
    private Member member;

}
