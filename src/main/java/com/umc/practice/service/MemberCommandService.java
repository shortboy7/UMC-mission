package com.umc.practice.service;

import com.umc.practice.converter.MemberConverter;
import com.umc.practice.converter.MemberPreferConverter;
import com.umc.practice.converter.MissionConverter;
import com.umc.practice.domain.FoodCategory;
import com.umc.practice.domain.Member;
import com.umc.practice.domain.MemberMission;
import com.umc.practice.domain.MemberPrefer;
import com.umc.practice.domain.Mission;
import com.umc.practice.domain.Review;
import com.umc.practice.domain.enums.MissionStatus;
import com.umc.practice.dto.MemberRequestDTO;
import com.umc.practice.dto.MemberResponseDTO;
import com.umc.practice.global.ResponseType.code.status.ErrorStatus;
import com.umc.practice.global.ResponseType.exception.GeneralException;
import com.umc.practice.repository.FoodCategoryRepository;
import com.umc.practice.repository.MemberMissionRepository;
import com.umc.practice.repository.MemberRepository;
import com.umc.practice.repository.MissionRepository;
import com.umc.practice.repository.ReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandService {
    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        Member member = MemberConverter.toMember(request);
        List<FoodCategory> categories = foodCategoryRepository.findAllById(request.getPreferCategory());
        List<MemberPrefer> memberPrefers = MemberPreferConverter.toMemberPreferList(categories);
        memberPrefers.forEach(prefer -> prefer.setMember(member));
        return memberRepository.save(member);
    }

    @Transactional
    public String challengeMission(
            MemberRequestDTO.NewChallengeMission request) {
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));
        Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        MemberMission memberMission = MissionConverter.toMemberMissionEntity(member, mission);
        member.addMission(memberMission);
        memberRepository.save(member);
        return "SUCCESS";
    }

    public MemberResponseDTO.ReviewPreViewListDTO getReviewList(Long userId, Integer page) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        Page<Review> reviewList = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return MemberConverter.memberReviewPreViewListDTO(reviewList);
    }

    public MemberResponseDTO.MissionListDTO getMissionList(Long userId, MissionStatus status, Integer page) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        Page<MemberMission> memberMissions = memberMissionRepository.findAllByMemberAndStatus(member, status,
                PageRequest.of(page, 10));
        return MemberConverter.missionListDTO(memberMissions);
    }
}
