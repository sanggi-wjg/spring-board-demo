package com.example.demo.common.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisMemberRepository extends CrudRepository<Account, Long> {
}
