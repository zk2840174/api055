package org.zerock.api01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.api01.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {


    Optional<Member> findByMid(String mid);

}
