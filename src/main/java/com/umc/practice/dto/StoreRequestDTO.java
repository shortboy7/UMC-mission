package com.umc.practice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class StoreRequestDTO {
    @Getter
    @AllArgsConstructor
    public static class NewStore {
        @Size(min = 1, max = 40)
        private String name;
        @Size(min = 1, max = 40)
        private String address;
        @NotNull
        private Float score;
    }
}
