package com.example.demo.member;

import com.example.demo.common.exception.AlreadyExist;
import com.example.demo.common.exception.NotExist;
import com.example.demo.common.redis.Account;
import com.example.demo.common.redis.RedisMemberRepository;
import com.example.demo.member.dto.JoinResponseDTO;
import com.example.demo.member.dto.LoginRequestDTO;
import com.example.demo.member.dto.JoinRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Api(value = "Member")
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
    public JoinResponseDTO create(@Valid @RequestBody JoinRequestDTO joinRequestDTO) {
        Optional<MemberEntity> byEmail = memberService.getByEmail(joinRequestDTO.getEmail());
        if (byEmail.isPresent()) {
            throw new AlreadyExist("email is exist");
        }

        MemberEntity newMember = memberService.create(modelMapper.map(joinRequestDTO, MemberEntity.class));
        return modelMapper.map(newMember, JoinResponseDTO.class);
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public Account login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<MemberEntity> memberByEmail = memberService.getByEmail(loginRequestDTO.getEmail());
        if (memberByEmail.isEmpty()) {
            throw new NotExist("not exist email");
        }

        Optional<MemberEntity> memberByAuth = memberService.getByAuth(loginRequestDTO);
        if (memberByAuth.isEmpty()) {
            throw new NotExist("invalid email or password");
        }

        Optional<Account> redisAccountById = redisMemberRepository.findById(memberByAuth.get().getId());
        if (redisAccountById.isEmpty()) {
            return redisMemberRepository.save(modelMapper.map(memberByAuth.get(), Account.class));
        } else {
            return redisAccountById.get();
        }
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody Account account) {
        redisMemberRepository.delete(account);
        return ResponseEntity.ok().build();
    }

}
