package com.umc.practice.service;

import com.umc.practice.converter.MemberConverter;
import com.umc.practice.converter.MemberPreferConverter;
import com.umc.practice.domain.FoodCategory;
import com.umc.practice.domain.Member;
import com.umc.practice.domain.MemberPrefer;
import com.umc.practice.dto.MemberRequestDTO;
import com.umc.practice.repository.FoodCategoryRepository;
import com.umc.practice.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandService {
    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        Member member = MemberConverter.toMember(request);
        List<FoodCategory> categories = foodCategoryRepository.findAllById(request.getPreferCategory());
        List<MemberPrefer> memberPrefers = MemberPreferConverter.toMemberPreferList(categories);
        memberPrefers.forEach(prefer -> prefer.setMember(member));
        return memberRepository.save(member);
    }
}
