package com.umc.practice.converter;

import com.umc.practice.domain.Member;
import com.umc.practice.domain.Review;
import com.umc.practice.domain.Store;
import com.umc.practice.dto.StoreRequestDTO;

public class StoreConverter {
    public static Store toStoreEntity(StoreRequestDTO.NewStore req) {
        return Store.builder()
                .name(req.getName())
                .address(req.getAddress())
                .score(req.getScore())
                .build();
    }
    public static Review toReviewEntity(StoreRequestDTO.NewStoreReview req,
                                        Member member, Store store) {
        return Review.builder()
                .title(req.getTitle())
                .score(req.getScore())
                .store(store)
                .member(member)
                .build();
    }
}
