package com.example.demo.member.dto;

import com.example.demo.member.MemberEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(value = "회원 가입 응답")
public class JoinResponseDTO {

    @ApiModelProperty(value = "Id", required = true, position = 1, example = "1")
    private Long id;

    @ApiModelProperty(value = "이름", required = true, position = 2, example = "GilDong")
    private String fullname;

    @ApiModelProperty(value = "이메일", required = true, position = 3, example = "user@mail.com")
    private String email;

}
