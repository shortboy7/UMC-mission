package com.umc.practice.repository;

import com.umc.practice.domain.Member;
import com.umc.practice.domain.MemberMission;
import com.umc.practice.domain.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findAllByMemberAndStatus(Member store, MissionStatus status, PageRequest pageRequest);
}
