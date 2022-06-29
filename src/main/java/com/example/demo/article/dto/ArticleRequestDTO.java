package com.example.demo.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "게시판 글 요청")
public class ArticleRequestDTO {

    @ApiModelProperty(value = "제목", required = true, position = 1, example = "게시판 제목")
    @NotBlank
    private String title;

    @ApiModelProperty(value = "내용", required = true, position = 2, example = "게시판 내용")
    @NotBlank
    private String content;

    @ApiModelProperty(value = "게시판 Id", required = true, position = 3, example = "1")
    private Long boardId;
    @ApiModelProperty(value = "멤버 Id", required = true, position = 4, example = "1")
    private Long memberId;

}
