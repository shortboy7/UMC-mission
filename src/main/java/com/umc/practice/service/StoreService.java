package com.umc.practice.service;

import com.umc.practice.converter.MissionConverter;
import com.umc.practice.converter.ReviewConverter;
import com.umc.practice.converter.StoreConverter;
import com.umc.practice.domain.Member;
import com.umc.practice.domain.Mission;
import com.umc.practice.domain.Review;
import com.umc.practice.domain.Store;
import com.umc.practice.dto.StoreRequestDTO;
import com.umc.practice.dto.StoreResponseDTO;
import com.umc.practice.dto.StoreResponseDTO.MissionDTO;
import com.umc.practice.dto.StoreResponseDTO.NewMission;
import com.umc.practice.dto.StoreResponseDTO.ReviewPreViewListDTO;
import com.umc.practice.global.ResponseType.code.status.ErrorStatus;
import com.umc.practice.global.ResponseType.exception.GeneralException;
import com.umc.practice.repository.MemberRepository;
import com.umc.practice.repository.MissionRepository;
import com.umc.practice.repository.ReviewRepository;
import com.umc.practice.repository.StoreRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

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
        return StoreConverter.toCreateReviewResultDTO(saved);
    }

    public StoreResponseDTO.NewMission enrollMission(Long storeId, StoreRequestDTO.NewStoreMission req) {
        Store store = storeRepository.findById(storeId).orElseThrow(
            () -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        Mission mission = MissionConverter.toMissionEntity(req, store);
        Mission save = missionRepository.save(mission);
        return new NewMission(save.getId(), mission.getReward(),
                mission.getDeadline(), mission.getMissionSpec());
    }

    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    public StoreResponseDTO.ReviewPreViewListDTO getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        Page<Review> storePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return ReviewConverter.reviewPreViewListDTO(storePage);
    }

    public StoreResponseDTO.MissionListDTO getMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        Page<Mission> missionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StoreConverter.missionListDTO(missionPage);
    }
}
