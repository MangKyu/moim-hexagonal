package com.mangkyu.moim.hexagonal.app.member.participant.domain;


import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Delegate;

@Getter
@Builder
@AllArgsConstructor
public class Participant {

    private Long id;
    private String limitedIngredient;
    private String introduce;

    @Delegate
    private Member member;

}
