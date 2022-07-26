package com.mangkyu.moim.hexagonal.app.member.participant.domain;


import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberException;
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

    public void addRole() {
        final MemberRole role = MemberRole.ROLE_PARTICIPANT;
        if (member.hasRole(role)) {
            throw new MemberException(MemberErrorCode.DUPLICATE_ROLE);
        }

        member.addRole(role);
    }

    public void addRole(Member member) {
        this.member = member;
        this.id = member.getId();
        addRole();
    }

}
