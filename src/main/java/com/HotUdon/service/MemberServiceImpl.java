package com.HotUdon.service;

import com.HotUdon.dto.MemberDTO;
import com.HotUdon.model.Member;
import com.HotUdon.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public MemberDTO findByLoginIdAndPassword(String loginId, String password) {
             Member member  = memberRepository.findByLoginIdAndPassword(loginId,password);
        return mapEntityToDTO(member);
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        Optional<Member> member = memberRepository.save(mapDTOToEntity(memberDTO));
        Member memberEntity = member.get();

        return mapEntityToDTO(memberEntity);
    }

    @Override
    public MemberDTO findById(Long id) {
        return null;
    }

    private MemberDTO mapEntityToDTO(Member member){
        MemberDTO  memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setCash(member.getCash());
        memberDTO.setGrade(member.getGrade());
        memberDTO.setLocation(member.getLocation());
        memberDTO.setPhone(member.getPhone());
        memberDTO.setPoint(member.getPoint());
        memberDTO.setPassword(member.getPassword());
        memberDTO.setDormantAccount(member.getDormantAccount());
        memberDTO.setLoginId(member.getLoginId());
        memberDTO.setLoginDay(member.getLoginDay());
        memberDTO.setNickName(member.getNickName());
        memberDTO.setStatus(member.getStatus());
        memberDTO.setRegDate(member.getRegDate());
        return memberDTO;
    }
    private Member mapDTOToEntity(MemberDTO memberDTO){
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setLoginId(memberDTO.getLoginId());
        member.setPassword(memberDTO.getPassword());
        member.setGrade(memberDTO.getGrade());
        member.setCash(memberDTO.getCash());
        member.setLocation(memberDTO.getLocation());
        member.setPhone(memberDTO.getPhone());
        member.setPoint(memberDTO.getPoint());
        member.setNickName(memberDTO.getNickName());
        member.setStatus(memberDTO.getStatus());
        member.setRegDate(memberDTO.getRegDate());
        member.setDormantAccount(memberDTO.getDormantAccount());
        return  member;
    }
}
