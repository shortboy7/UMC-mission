package com.umc.practice.dto;

import com.umc.practice.validator.ExistUser;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    @Getter
    @AllArgsConstructor
    public static class NewStoreReview {
        @NotBlank
        private String title;
        @NotNull
        private Float score;
        @NotNull
        @ExistUser
        private Long memberId;
    }
    @Getter
    @AllArgsConstructor
    public static class NewStoreMission {
        @NotNull
        private Integer reward;
        @NotNull
        private LocalDate deadline;
        @NotBlank
        private String missionSpec;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ReviewDTO {
        @NotEmpty
        private String title;
        @NotNull
        private Float score;
        @NotEmpty
        private String body;
    }
}
