package com.example.demo.board.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel(value = "게시판 요청")
public class BoardRequestDTO {

    @ApiModelProperty(value = "이름", required = true, position = 1, example = "게시판 이름")
    @NotBlank
    private String name;

}
