package com.HotUdon.service.member;

import com.HotUdon.dto.MemberDTO;
import com.HotUdon.model.Member;
import com.HotUdon.model.Role;
import com.HotUdon.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberDTO findByLoginIdAndPassword(String loginId, String password) {
             Member member  = memberRepository.findByLoginIdAndPassword(loginId,password);
        return mapEntityToDTO(member);
    }

        @Override
        public int save(MemberDTO memberDTO) {
            System.out.println("memberDTOService = " + memberDTO);
            Member member = Member.builder()
                    .loginId(memberDTO.getLoginId())
                    .password(passwordEncoder.encode(memberDTO.getPassword()))
                    .nickName(memberDTO.getNickName())
                    .phone(memberDTO.getPhone())
                    .email(memberDTO.getEmail())
                    .postCode(memberDTO.getPostCode())
                    .address(memberDTO.getAddress())
                    .addressDetail(memberDTO.getAddressDetail())
                    .loginDay(memberDTO.getLoginDay())
                    .role(Role.USER)
                    .build();
            memberRepository.save(member);
            Long memberId = member.getId();
            return memberId != null ? memberId.intValue() : 0;
        }


    @Override
    public MemberDTO findByLoginId(String id) {
        Optional<Member>  memberOptional  = memberRepository.findByLoginId(id);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return mapEntityToDTO(member);
        } else {
            return null;
        }
    }
    @Override
    public  MemberDTO findByNickname(String nick){
        Optional<Member> memberOptional  = memberRepository.findByNickName(nick);
        if(memberOptional.isPresent()){
            Member member = memberOptional.get();
            return mapEntityToDTO(member);
        }else {
            return null;
        }
        }

    @Override
    @Transactional
    public void updateMember(Long id, MemberDTO memberDTO) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        if (memberDTO.getPassword() != null && !memberDTO.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
            member.setPassword(encodedPassword);
        }if (memberDTO.getAddress() != null && !memberDTO.getAddress().isEmpty()) {
                member.setAddress(memberDTO.getAddress());
                member.setPostCode(memberDTO.getPostCode());
        }if(memberDTO.getEmail() !=null && !memberDTO.getEmail().isEmpty()){
            member.setEmail(memberDTO.getEmail());
            }if(memberDTO.getNickName() != null && !memberDTO.getNickName().isEmpty()){
            member.setNickName(memberDTO.getNickName());
        }if(memberDTO.getAddressDetail() != null && !memberDTO.getAddressDetail().isEmpty()){
        member.setAddressDetail(memberDTO.getAddressDetail());
        }
        memberRepository.save(member);
    }
    @Override
    public MemberDTO findById(Long id) {
       Optional<Member> memberOptional = memberRepository.findById(id);
       if(memberOptional.isPresent()){
           Member member = memberOptional.get();
           System.out.println("memberService = " + member);
          return mapEntityToDTO(member);
       }
        throw new EntityNotFoundException("Member not found with id: " + id);
    }

    private MemberDTO mapEntityToDTO(Member member){
        MemberDTO  memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setCash(member.getCash());
        memberDTO.setGrade(member.getGrade());
        memberDTO.setPostCode(member.getPostCode());
        memberDTO.setAddress(member.getAddress());
        memberDTO.setAddressDetail(member.getAddressDetail());
        memberDTO.setPhone(member.getPhone());
        memberDTO.setPoint(member.getPoint());
        memberDTO.setPassword(member.getPassword());
        memberDTO.setDormantAccount(member.getDormantAccount());
        memberDTO.setLoginId(member.getLoginId());
        memberDTO.setLoginDay(member.getLoginDay());
        memberDTO.setNickName(member.getNickName());
        memberDTO.setStatus(member.getStatus());
        memberDTO.setRegDate(member.getRegDate());
        memberDTO.setEmail(member.getEmail());
        return memberDTO;
    }
    private Member mapDTOToEntity(MemberDTO memberDTO){
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setLoginId(memberDTO.getLoginId());
        member.setPassword(memberDTO.getPassword());
        member.setGrade(memberDTO.getGrade());
        member.setCash(memberDTO.getCash());
        member.setPostCode(memberDTO.getPostCode());
        member.setAddress(memberDTO.getAddress());
        member.setAddressDetail(memberDTO.getAddressDetail());
        member.setPhone(memberDTO.getPhone());
        member.setPoint(memberDTO.getPoint());
        member.setNickName(memberDTO.getNickName());
        member.setStatus(memberDTO.getStatus());
        member.setRegDate(memberDTO.getRegDate());
        member.setDormantAccount(memberDTO.getDormantAccount());
        member.setEmail(memberDTO.getEmail());
        return  member;
    }
}
