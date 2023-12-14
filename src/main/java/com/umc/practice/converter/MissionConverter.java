package com.umc.practice.converter;

import com.umc.practice.domain.Member;
import com.umc.practice.domain.MemberMission;
import com.umc.practice.domain.Mission;
import com.umc.practice.domain.Store;
import com.umc.practice.domain.enums.MissionStatus;
import com.umc.practice.dto.MemberRequestDTO;
import com.umc.practice.dto.StoreRequestDTO;

public class MissionConverter {
    public static Mission toMissionEntity(StoreRequestDTO.NewStoreMission req, Store store) {
        return Mission.builder()
                .missionSpec(req.getMissionSpec())
                .reward(req.getReward())
                .deadline(req.getDeadline())
                .store(store)
                .build();
    }
    public static MemberMission toMemberMissionEntity(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}
