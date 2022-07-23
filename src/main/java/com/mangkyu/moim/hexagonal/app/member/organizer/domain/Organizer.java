package com.mangkyu.moim.hexagonal.app.member.organizer.domain;

import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Organizer {

    private Long id;
    private String belongs;
    private Member member;

}
