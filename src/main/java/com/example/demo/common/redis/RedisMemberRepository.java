package com.example.demo.common.redis;

import com.example.demo.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedisMemberRepository extends JpaRepository<MemberEntity, Long> {
}
