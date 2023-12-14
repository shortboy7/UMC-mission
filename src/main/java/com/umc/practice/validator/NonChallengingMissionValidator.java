package com.umc.practice.validator;

import com.umc.practice.domain.Member;
import com.umc.practice.domain.MemberMission;
import com.umc.practice.dto.MemberRequestDTO;
import com.umc.practice.dto.MemberRequestDTO.NewChallengeMission;
import com.umc.practice.global.ResponseType.code.status.ErrorStatus;
import com.umc.practice.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NonChallengingMissionValidator implements ConstraintValidator<ExistStore,
        MemberRequestDTO.NewChallengeMission> {
    private final MemberRepository memberRepository;
    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(NewChallengeMission value, ConstraintValidatorContext context) {
        Optional<Member> optMember = memberRepository.findById(value.getUserId());
        if (optMember.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString())
                    .addConstraintViolation();
        }
        Member member = optMember.get();
        List<MemberMission> alreadyMission = member.getMemberMissionList().stream()
                .filter(memberMission -> memberMission.getMission().getId().equals(value.getMissionId()))
                .collect(Collectors.toList());
        if (!alreadyMission.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_DUP_CHALLENGE.toString())
                    .addConstraintViolation();
        }
        return true;
    }
}
