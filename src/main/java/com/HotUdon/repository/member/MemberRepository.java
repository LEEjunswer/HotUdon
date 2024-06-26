package com.HotUdon.repository.member;

import com.HotUdon.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member ,Long> {

    Member findByLoginIdAndPassword(String loginId,String password);
    Optional<Member> findByLoginId(String loginId);
    boolean existsByNickName(String nickName);
    List<Member> findAllBy();
    Optional<Member> findByNickName(String nickName);

}
