package com.HotUdon.mapper;

import com.HotUdon.dto.MemberDTO;
import com.HotUdon.model.Member;

public class MemberMapper {

    public static MemberDTO mapEntityToDTO(Member member){
        if(member == null){
            return  null;
        }
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
    public static Member mapDtoToEntity(MemberDTO memberDTO){
        if(memberDTO == null){
            return null;
        }
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
