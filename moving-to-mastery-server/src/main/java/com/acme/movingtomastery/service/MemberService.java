package com.acme.movingtomastery.service;

import com.acme.movingtomastery.dto.MemberDTO;
import com.acme.movingtomastery.entity.Member;
import com.acme.movingtomastery.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public Collection<Member> findAll() {
        return memberRepository.findAll();
    }

    public Collection<Member> findNonDeleted() {
        return memberRepository.findNonDeleted();
    }

    public Member findById(final UUID id) {
        return memberRepository.findById(id.toString());
    }

    public Member findByPaypal(final String paypal) {
        return memberRepository.findByPaypal(paypal);
    }

    public Member create(final MemberDTO memberDTO) {
        var memberFromDatabase = memberRepository.findByPaypal(memberDTO.paypal());
        if (!Objects.equals(memberFromDatabase, null)) {
            log.info("Member {} already exist.", memberDTO);
            return null;
        }
        var member = memberDTO.toMember();
        member.setId(UUID.randomUUID());
        return memberRepository.create(member);
    }

    public Member update(final Member member) {
        return memberRepository.update(member);
    }

    public Member delete(final UUID id) {
        return memberRepository.delete(id.toString());
    }
}
