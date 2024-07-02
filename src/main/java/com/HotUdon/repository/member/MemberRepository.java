package com.HotUdon.repository.member;

import com.HotUdon.model.ChatRoom;
import com.HotUdon.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member ,Long>,MemberCustomRepository {

    Member findByLoginIdAndPassword(String loginId,String password);
    Optional<Member> findByLoginId(String loginId);
    boolean existsByNickName(String nickName);
    List<Member> findAllBy();
    Optional<Member> findByNickName(String nickName);


}
