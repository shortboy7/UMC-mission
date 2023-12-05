package com.umc.practice.converter;

import com.umc.practice.domain.Mission;
import com.umc.practice.domain.Store;
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
}
