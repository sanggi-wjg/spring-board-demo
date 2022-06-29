package com.example.demo.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "게시판 글 응답")
public class ArticleResponseDTO {

    @ApiModelProperty(value = "제목", required = true, position = 1, example = "게시판 제목")
    private String title;

    @ApiModelProperty(value = "내용", required = true, position = 2, example = "게시판 내용")
    private String content;

    @ApiModelProperty(value = "생성 시간", required = true, position = 5, example = "게시판 생성 시간")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "게시판 Id", required = true, position = 3, example = "1")
    private Long boardId;

    @ApiModelProperty(value = "멤버 Id", required = true, position = 4, example = "1")
    private Long memberId;

}
