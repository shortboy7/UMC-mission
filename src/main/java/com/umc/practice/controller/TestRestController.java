package com.umc.practice.controller;

import com.umc.practice.converter.TempConverter;
import com.umc.practice.dto.TempResponse.TempExceptionDTO;
import com.umc.practice.dto.TempResponse.TempTestDTO;
import com.umc.practice.global.ResponseType.code.BaseResponse;
import com.umc.practice.service.TempQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestRestController {
    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public BaseResponse<TempTestDTO> testAPI() {
        return BaseResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public BaseResponse<TempExceptionDTO> exceptionAPI(@RequestParam Integer flag) {
        tempQueryService.CheckFlag(flag);
        return BaseResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
}
