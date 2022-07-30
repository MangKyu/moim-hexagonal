package com.mangkyu.moim.hexagonal.app.member.common.domain;

import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
public class MemberInfo {

    private Long id;
    private String name;
    private LocalDate birth;
    private Gender gender;
    private String email;
    private String loginId;
    private String password;
    private String belongs;
    private String limitedIngredient;
    private String introduce;
    private Set<MemberRole> roles = new HashSet<>();

    public MemberInfo(final Long id) {
        this.id = id;
    }

    public void update(final Member member) {
        this.name = member.getName();
        this.birth = member.getBirth();
        this.gender = member.getGender();
        this.email = member.getEmail();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.roles = member.getRoles();
    }

    public void update(final Participant participant) {
        this.limitedIngredient = participant.getLimitedIngredient();
        this.introduce = participant.getIntroduce();
    }

    public void update(final Organizer organizer) {
        this.belongs = organizer.getBelongs();
    }

}