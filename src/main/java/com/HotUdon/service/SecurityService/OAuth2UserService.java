package com.HotUdon.service.SecurityService;

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
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 카카오 사용자 정보를 가져오기
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String loginId = String.valueOf(attributes.get("id"));

        Map<String, Object> properties = castToMap(attributes.get("properties"));
        String nickname = properties != null ? String.valueOf(properties.get("nickname")) : "";

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

        return new DefaultOAuth2User(
                authorities,
                attributes,
                "id");
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