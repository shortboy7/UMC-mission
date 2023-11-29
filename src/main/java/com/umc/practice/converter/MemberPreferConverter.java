package com.umc.practice.converter;

import com.umc.practice.domain.FoodCategory;
import com.umc.practice.domain.MemberPrefer;
import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {
    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategories) {
        return foodCategories.stream()
                .map(foodCategory -> MemberPrefer.builder()
                        .foodCategory(foodCategory)
                        .build())
                .collect(Collectors.toList());
    }
}
