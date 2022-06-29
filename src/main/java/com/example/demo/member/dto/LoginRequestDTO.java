package com.example.demo.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(value = "로그인 요청")
public class LoginRequestDTO {

    @ApiModelProperty(value = "이메일", required = true, position = 1, example = "user@mail.com")
    @NotBlank
    private String email;

    @ApiModelProperty(value = "비밀번호", required = true, position = 2, example = "****")
    @NotBlank
    private String password;

    public void encryptPassword() {
        setPassword("-salt-" + password + "-salt-");
    }
}
