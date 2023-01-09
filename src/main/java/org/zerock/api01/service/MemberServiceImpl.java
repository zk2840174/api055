package org.zerock.api01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.api01.dto.MemberDTO;
import org.zerock.api01.entity.Member;
import org.zerock.api01.repository.MemberRepository;

import jakarta.transaction.Transactional;
import java.util.Optional;


@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{


    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberDTO getMember(String mid, String mpw) {


        log.info("mid..........." + mid);

        Optional<Member> result =  memberRepository.findByMid(mid);

        Member member = result.orElseThrow();

        log.info("member: " +  member);

        if(passwordEncoder.matches(mpw, member.getMpw()) ) {

            return modelMapper.map(member, MemberDTO.class);
        }
        return null;
    }
}
