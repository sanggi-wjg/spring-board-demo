package com.example.demo.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "회원정보")
public class MemberDTO {

    @ApiModelProperty(value = "이름", required = true, position = 1, example = "GilDong")
    @NotBlank
    private String fullname;

    @ApiModelProperty(value = "이메일", required = true, position = 2, example = "user@mail.com")
    @NotBlank
    private String email;

    @ApiModelProperty(value = "비밀번호", required = true, position = 3, example = "****")
    @NotBlank
    private String password;

    public void encryptPassword() {
        setPassword("-salt-" + password + "-salt-");
    }
}
