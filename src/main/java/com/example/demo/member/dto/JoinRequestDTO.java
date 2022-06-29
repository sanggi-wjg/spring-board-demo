package com.example.demo.member.dto;

import com.example.demo.member.MemberEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(value = "회원 가입 요청")
public class JoinRequestDTO {

    @ApiModelProperty(value = "이름", required = true, position = 1, example = "GilDong")
    @NotBlank
    private String fullname;

    @ApiModelProperty(value = "이메일", required = true, position = 2, example = "user@mail.com")
    @NotBlank
    private String email;

    @ApiModelProperty(value = "비밀번호", required = true, position = 3, example = "****")
    @NotBlank
    private String password;

}
