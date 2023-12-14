package com.umc.practice.converter;

import com.umc.practice.domain.Member;
import com.umc.practice.domain.Review;
import com.umc.practice.domain.Store;
import com.umc.practice.dto.StoreRequestDTO;
import com.umc.practice.dto.StoreResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

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


    public static Review toReview(StoreRequestDTO.ReviewDTO request){
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static StoreResponseDTO.NewReview toCreateReviewResultDTO(Review review){
        return StoreResponseDTO.NewReview.builder()
                .id(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
