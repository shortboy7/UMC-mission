package com.umc.practice.dto;

import com.umc.practice.validator.ExistCategories;
import com.umc.practice.validator.ExistUser;
import com.umc.practice.validator.NonChallengingMission;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberRequestDTO {
    @Getter
    @AllArgsConstructor
    public static class JoinDto{
        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;

        @ExistCategories
        List<Long> preferCategory;
    }
    @Getter
    @AllArgsConstructor
    @NonChallengingMission
    public static class NewChallengeMission {
        @NotNull
        @ExistUser
        private Long userId;
        @NotNull
        private Long missionId;
    }
}
