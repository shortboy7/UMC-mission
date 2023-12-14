package com.umc.practice.controller;

import com.umc.practice.converter.MemberConverter;
import com.umc.practice.domain.Member;
import com.umc.practice.dto.MemberRequestDTO;
import com.umc.practice.dto.MemberResponseDTO;
import com.umc.practice.global.ResponseType.code.BaseResponse;
import com.umc.practice.service.MemberCommandService;
import com.umc.practice.validator.NonChallengingMission;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberCommandService memberService;

    @PostMapping("/")
    public BaseResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        Member member = memberService.joinMember(request);
        return BaseResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
    @PostMapping("/challenge/{missionId}")
    public BaseResponse<String> challenge(
            @RequestBody @Valid MemberRequestDTO.NewChallengeMission request) {
        String success = this.memberService.challengeMission(request);
        return BaseResponse.onSuccess(success);
    }
}
