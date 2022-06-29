package com.example.demo.member;

import com.example.demo.member.dto.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberEntity create(MemberEntity memberEntity) {
        memberEntity.encryptPassword();
        return memberRepository.save(memberEntity);
    }

    public Optional<MemberEntity> getByAuth(LoginRequestDTO loginRequestDTO) {
        loginRequestDTO.encryptPassword();
        return memberRepository.findByEmailAndPassword(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
    }

    public Optional<MemberEntity> getByEmail(String email) {
        return memberRepository.findByEmail(email);
    }


    public Optional<MemberEntity> getById(Long id) {
        return memberRepository.findById(id);
    }

}
