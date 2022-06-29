package com.example.demo.member;

import com.example.demo.member.dto.LoginDTO;
import com.example.demo.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberEntity createMember(MemberEntity memberEntity) {
        memberEntity.encryptPassword();
        return memberRepository.save(memberEntity);
    }

    public Optional<MemberEntity> checkMember(LoginDTO loginDTO) {
        loginDTO.encryptPassword();
        return memberRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
    }

    public Optional<MemberEntity> getByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
