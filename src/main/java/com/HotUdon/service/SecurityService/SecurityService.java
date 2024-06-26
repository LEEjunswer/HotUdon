package com.HotUdon.service.SecurityService;

import com.HotUdon.config.oauth.PrincipalDetails;
import com.HotUdon.model.Member;
import com.HotUdon.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {


    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if (member == null) {
            throw new UsernameNotFoundException("User not found with loginId: " + loginId);
        }
        System.out.println("member = " + member);
        if(member.isPresent()){
            System.out.println(" 멤버 디테일 객체 생성 !!! " + member.get());
            return new PrincipalDetails(member.get()); // 이 함수가 종료가 될때 @Authentication 객체가 만들어진다
        }
        return null;
    }
}
