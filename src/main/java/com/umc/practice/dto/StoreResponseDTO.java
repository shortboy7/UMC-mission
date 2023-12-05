package com.umc.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class StoreResponseDTO {
    @Getter
    @AllArgsConstructor
    public static class NewStore {
        private Long id;
        private String name;
        private String address;
        private Float score;
    }
}
