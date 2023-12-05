package com.umc.practice.service;

import com.umc.practice.converter.StoreConverter;
import com.umc.practice.domain.Member;
import com.umc.practice.domain.Review;
import com.umc.practice.domain.Store;
import com.umc.practice.dto.StoreRequestDTO;
import com.umc.practice.dto.StoreResponseDTO;
import com.umc.practice.repository.MemberRepository;
import com.umc.practice.repository.ReviewRepository;
import com.umc.practice.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    public StoreResponseDTO.NewStore enrollNewStore(StoreRequestDTO.NewStore req) {
        Store store = this.storeRepository
                .save(StoreConverter
                        .toStoreEntity(req));
        return new StoreResponseDTO.NewStore(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getScore()
        );
    }
    public StoreResponseDTO.NewReview enrollReview(Long storeId, StoreRequestDTO.NewStoreReview req) {
        Member member = memberRepository.findById(req.getMemberId()).orElseThrow();
        Store store = storeRepository.findById(storeId).orElseThrow();
        Review review = StoreConverter.toReviewEntity(req, member, store);
        Review saved = reviewRepository.save(review);
        return new StoreResponseDTO.NewReview(saved.getId(), saved.getTitle(), saved.getScore());
    }
}
