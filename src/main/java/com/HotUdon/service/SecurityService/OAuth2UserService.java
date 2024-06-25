package com.HotUdon.service.SecurityService;

import com.HotUdon.config.oauth.PrincipalDetails;
import com.HotUdon.model.Member;
import com.HotUdon.model.Role;
import com.HotUdon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("OAuth2UserService 진입체크");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("진입체크");
        Map<String, Object> attributes = oAuth2User.getAttributes();
        System.out.println("Api 로그인 attributes = " + attributes);
        Map<String, Object> response = castToMap(attributes.get("response"));
        String loginId = String.valueOf(response.get("email"));
        String nickname = String.valueOf(response.get("nickname"));
        Map<String, Object> properties = castToMap(attributes.get("properties"));

        // 사용자 정보를 매핑하는 로직 추가
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);
        Member member;
        if (memberOptional.isPresent()) {
            member = memberOptional.get();
            member.setNickName(nickname);
        } else {
            nickname = generateUniqueNickname(nickname);
            member = new Member();
            member.setLoginId(loginId);
            member.setNickName(nickname);
            member.setRole(Role.USER);
            memberRepository.save(member);
        }

        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_" + member.getRole().name()));

        return new PrincipalDetails(member, attributes);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> castToMap(Object obj) {
        if (obj instanceof Map<?, ?>) {
            return (Map<String, Object>) obj;
        }
        return Collections.emptyMap();
    }

    private String generateUniqueNickname(String baseNickname) {
        String uniqueNickname = baseNickname;
        Random random = new Random();
        // 닉네임이 중복일시 랜덤으로 숫자 추가
        while (memberRepository.existsByNickName(uniqueNickname)) {
            uniqueNickname = baseNickname + random.nextInt(10000);
        }
        return uniqueNickname;
    }
}