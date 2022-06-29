package com.example.demo.member;

import com.example.demo.common.exception.AlreadyExist;
import com.example.demo.common.exception.NotExist;
import com.example.demo.common.redis.RedisMemberRepository;
import com.example.demo.member.dto.LoginDTO;
import com.example.demo.member.dto.MemberDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Api(value = "멤버")
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RedisMemberRepository redisMemberRepository;

    @ApiOperation(value = "회원가입")
    @ApiResponse(code = 201, message = "생성 성공", response = MemberEntity.class)
    @PostMapping("/signup")
    public MemberEntity create(@RequestBody MemberDTO memberDTO) {
        Optional<MemberEntity> byEmail = memberService.getByEmail(memberDTO.getEmail());
        if (byEmail.isPresent()) {
            throw new AlreadyExist("email is exist");
        }

        MemberEntity newMember = modelMapper.map(memberDTO, MemberEntity.class);
        return memberService.createMember(newMember);
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public MemberEntity login(@RequestBody LoginDTO loginDTO) {
        Optional<MemberEntity> memberByEmail = memberService.getByEmail(loginDTO.getEmail());

        if (memberByEmail.isEmpty()) {
            throw new NotExist("not exist email");
        }
        Optional<MemberEntity> memberByAuth = memberService.checkMember(loginDTO);
        if (memberByAuth.isEmpty()) {
            throw new NotExist("invalid email or password");
        }

        Optional<MemberEntity> redisMemberById = redisMemberRepository.findById(memberByAuth.get().getId());
        if (redisMemberById.isEmpty()) {
            return redisMemberRepository.save(memberByAuth.get());
        } else {
            return redisMemberById.get();
        }
    }

    @ApiOperation(value = "로그아웃")
    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody MemberDTO memberDTO) {
        return ResponseEntity.ok().build();
    }

}
