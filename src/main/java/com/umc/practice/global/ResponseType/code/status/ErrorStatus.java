package com.umc.practice.global.ResponseType.code.status;

import com.umc.practice.global.ResponseType.code.BaseErrorCode;
import com.umc.practice.global.ResponseType.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    // 일반 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // page 에러
    PAGE_SHOULD_BE_POSITIVE(HttpStatus.BAD_REQUEST, "PAGE001", "페이지는 양수여야 합니다"),

    // 멤버 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    MEMBER_NOT_CHALLENGE_SUCH_MISSION(HttpStatus.BAD_REQUEST, "MEMBER4003", "도전 중인 미션이 아닙니다"),
    MEMBER_ALREADY_COMPLETE_SUCH_MISSION(HttpStatus.BAD_REQUEST, "MEMBER4003", "이미 도전이 완료된 미션입니다"),

    // 게시글 관련 에러
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    // FoodCategory 에러
    NO_SUCH_FOOD_CATEGORY(HttpStatus.NOT_FOUND, "FOODCATEGORY4001", "일치하는 카테고리가 없습니다"),
    // 매장 에러
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "일치하는 매장이 없습니다"),
    // 미션 에러
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "일치하는 미션이 없습니다"),
    MISSION_DUP_CHALLENGE(HttpStatus.CONFLICT, "MISSION4002", "이미 도전 중인 미션입니다"),

    // Test 에러
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
