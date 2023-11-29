package com.umc.practice.controller;

import com.umc.practice.dto.MemberRequestDTO;
import com.umc.practice.dto.MemberResponseDTO;
import com.umc.practice.global.ResponseType.code.BaseResponse;
import com.umc.practice.service.MemberCommandService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberCommandService service;

    public BaseResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {
        return null;
    }
}
