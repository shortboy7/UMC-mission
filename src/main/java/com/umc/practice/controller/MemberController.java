package com.umc.practice.controller;

import com.umc.practice.converter.MemberConverter;
import com.umc.practice.domain.Member;
import com.umc.practice.domain.enums.MissionStatus;
import com.umc.practice.dto.MemberRequestDTO;
import com.umc.practice.dto.MemberResponseDTO;
import com.umc.practice.dto.MemberResponseDTO.MissionListDTO;
import com.umc.practice.dto.MemberResponseDTO.ReviewPreViewListDTO;
import com.umc.practice.global.ResponseType.code.BaseResponse;
import com.umc.practice.service.MemberCommandService;
import com.umc.practice.validator.ExistUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{userId}/reviews")
    @Operation(summary = "특정 유저의 리뷰 목록 조회 API",description = "특정 유저의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "멤버의 아이디, path variable 입니다!")
    })
    public BaseResponse<MemberResponseDTO.ReviewPreViewListDTO> getReviewList(
            @ExistUser @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "page") Integer page) {
        ReviewPreViewListDTO reviewList = memberService.getReviewList(userId, page);
        return BaseResponse.onSuccess(reviewList);
    }

    @GetMapping("/{userId}/missions")
    @Operation(summary = "특정 유저의 도전 중 미션 목록 조회 API",description = "특정 유저의 도전 중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호, 그리고 미션 상태를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "멤버의 아이디, path variable 입니다!"),
    })
    public BaseResponse<MemberResponseDTO.MissionListDTO> getChallengingMissionList(
            @ExistUser @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "status") MissionStatus status,
            @RequestParam(name = "page") Integer page) {
        MissionListDTO missionList = memberService.getMissionList(userId, status, page);
        return BaseResponse.onSuccess(missionList);
    }

    @PatchMapping("/{userId}/{missionId}")
    @Operation(summary = "특정 유저의 특정 미션을 진행완료로 바꾸는 API",description = "특정 유저의 특정 미션을 진행완료로 바꾸 API이며, 페이징을 포함합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "missionId", description = "미션의 아이디, path variable 입니다!")
    })
    public BaseResponse<Long> makeMissionComplete(
            @ExistUser @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "missionId") Long missionId) {
        Long memberMissionId = memberService.makeMissionComplete(userId, missionId);
        return BaseResponse.onSuccess(memberMissionId);
    }
}
