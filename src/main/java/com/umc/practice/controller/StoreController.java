package com.umc.practice.controller;

import com.umc.practice.dto.StoreRequestDTO;
import com.umc.practice.dto.StoreResponseDTO;
import com.umc.practice.dto.StoreResponseDTO.MissionDTO;
import com.umc.practice.dto.StoreResponseDTO.NewMission;
import com.umc.practice.dto.StoreResponseDTO.NewReview;
import com.umc.practice.dto.StoreResponseDTO.NewStore;
import com.umc.practice.dto.StoreResponseDTO.ReviewPreViewListDTO;
import com.umc.practice.global.ResponseType.code.BaseResponse;
import com.umc.practice.service.StoreService;
import com.umc.practice.validator.ExistStore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/")
    public BaseResponse<StoreResponseDTO.NewStore> newStore(
            @RequestBody @Valid StoreRequestDTO.NewStore request) {
        NewStore newStore = storeService.enrollNewStore(request);
        return BaseResponse.onSuccess(newStore);
    }
    @PostMapping("/{storeId}/review")
    public BaseResponse<StoreResponseDTO.NewReview> newReview(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid StoreRequestDTO.NewStoreReview request) {
        NewReview newReview = this.storeService.enrollReview(storeId, request);
        return BaseResponse.onSuccess(newReview);
    }
    @PostMapping("/{storeId}/mission")
    public BaseResponse<StoreResponseDTO.NewMission> newMission(
            @ExistStore @PathVariable("storeId") Long storeId,
            @RequestBody @Valid StoreRequestDTO.NewStoreMission request) {
        NewMission newMission = this.storeService.enrollMission(storeId, request);
        return BaseResponse.onSuccess(newMission);
    }
    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public BaseResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page) {
        ReviewPreViewListDTO reviewList = storeService.getReviewList(storeId, page);
        return BaseResponse.onSuccess(reviewList);
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public BaseResponse<List<StoreResponseDTO.MissionDTO>> getMissionList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page) {
        List<MissionDTO> missionList = storeService.getMissionList(storeId, page);
        return BaseResponse.onSuccess(missionList);
    }
}
