package com.umc.practice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreResponseDTO {
    @Getter
    @AllArgsConstructor
    public static class NewStore {
        private Long id;
        private String name;
        private String address;
        private Float score;
    }
    @Builder
    @Getter
    @AllArgsConstructor
    public static class NewReview{
        private Long id;
        private String title;
        private Float score;
        private LocalDateTime createdAt;
    }
    @Getter
    @AllArgsConstructor
    public static class NewMission {
        private Long id;
        private Integer reward;
        private LocalDate deadline;
        private String missionSpec;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO{
        private List<ReviewPreViewDTO> reviewList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO{
        private String ownerNickname;
        private Float score;
        private String body;
        private LocalDate createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionDTO {
        private Long id;
        private Integer reward;
        private LocalDate deadline;
        private String missionSpec;
    }
}
