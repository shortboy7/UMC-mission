package com.umc.practice.converter;

import com.umc.practice.dto.TempResponse.TempExceptionDTO;
import com.umc.practice.dto.TempResponse.TempTestDTO;

public class TempConverter {
    public static TempTestDTO toTempTestDTO() {
        return TempTestDTO.builder()
                .testString("This is Test!")
                .build();
    }
    public static TempExceptionDTO toTempExceptionDTO(Integer flag) {
        return TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
