package com.umc.practice.converter;

import com.umc.practice.domain.Member;
import com.umc.practice.domain.MemberMission;
import com.umc.practice.domain.Mission;
import com.umc.practice.domain.Review;
import com.umc.practice.domain.enums.Gender;
import com.umc.practice.domain.enums.MissionStatus;
import com.umc.practice.dto.MemberRequestDTO;
import com.umc.practice.dto.MemberResponseDTO;
import com.umc.practice.dto.MemberResponseDTO.MissionDTO;
import com.umc.practice.dto.MemberResponseDTO.MissionListDTO;
import com.umc.practice.dto.MemberResponseDTO.ReviewPreViewDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public class MemberConverter {
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Member toMember(MemberRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .age(request.getBirthYear() - LocalDate.now().getYear() + 1)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .memberAgreeList(new ArrayList<>())
                .memberMissionList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDTO.ReviewPreViewDTO memberReviewPreViewDTO(Review review) {
        return MemberResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static MemberResponseDTO.ReviewPreViewListDTO memberReviewPreViewListDTO(Page<Review> reviewList){

        List<ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(MemberConverter::memberReviewPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static MemberResponseDTO.MissionDTO missionDTO(Mission mission) {
        return MemberResponseDTO.MissionDTO.builder()
                .id(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .build();
    }
    public static MemberResponseDTO.MissionListDTO missionListDTO(Page<MemberMission> memberMissionList) {
        List<MissionDTO> missions = memberMissionList.stream()
                .map(memberMission -> missionDTO(memberMission.getMission()))
                .collect(Collectors.toList());

        return MissionListDTO.builder()
                .missionList(missions)
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(missions.size())
                .build();
    }

}
