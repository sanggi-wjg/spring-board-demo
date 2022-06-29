package com.example.demo.article;


import com.example.demo.article.dto.ArticleRequestDTO;
import com.example.demo.article.dto.ArticleResponseDTO;
import com.example.demo.board.BoardEntity;
import com.example.demo.board.BoardService;
import com.example.demo.common.exception.NotExist;
import com.example.demo.member.MemberEntity;
import com.example.demo.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Api(value = "Board")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Create article")
    @PostMapping("/articles")
    public ArticleResponseDTO createArticle(@Valid @RequestBody ArticleRequestDTO articleRequestDTO) {
        Optional<BoardEntity> findBoardById = boardService.getById(articleRequestDTO.getBoardId());
        if (findBoardById.isEmpty()) {
            throw new NotExist("board not exist");
        }

        Optional<MemberEntity> findMemberById = memberService.getById(articleRequestDTO.getMemberId());
        if (findMemberById.isEmpty()) {
            throw new NotExist("member not exist");
        }

        ArticleEntity article = new ArticleEntity();
        article.setTitle(articleRequestDTO.getTitle());
        article.setContent(articleRequestDTO.getContent());
        article.setBoard(findBoardById.get());
        article.setMember(findMemberById.get());

        ArticleEntity newArticle = articleService.create(article);
        return ArticleResponseDTO.builder()
                .title(newArticle.getTitle())
                .content(newArticle.getContent())
                .createdAt(newArticle.getCreatedAt())
                .boardId(newArticle.getBoard().getId())
                .memberId(newArticle.getMember().getId())
                .build();
    }

    @ApiOperation(value = "Get article")
    @GetMapping("/articles/{article_id}")
    public ArticleResponseDTO article(@PathVariable("article_id") Long articleId) {
        Optional<ArticleEntity> findArticleById = articleService.getById(articleId);
        if (findArticleById.isEmpty()) {
            throw new NotExist("article not exist");
        } else {
            return ArticleResponseDTO.builder()
                    .title(findArticleById.get().getTitle())
                    .content(findArticleById.get().getContent())
                    .createdAt(findArticleById.get().getCreatedAt())
                    .boardId(findArticleById.get().getBoard().getId())
                    .memberId(findArticleById.get().getMember().getId())
                    .build();
        }
    }

    @ApiOperation(value = "Update article")
    @PutMapping("/articles/{article_id}")
    public ArticleResponseDTO updateArticle(@PathVariable("article_id") Long articleId) {
        Optional<ArticleEntity> findArticleById = articleService.getById(articleId);
        if (findArticleById.isEmpty()) {
            throw new NotExist("article not exist");
        } else {
            findArticleById.get().update(findArticleById.get().getTitle(), findArticleById.get().getContent());
            return ArticleResponseDTO.builder()
                    .title(findArticleById.get().getTitle())
                    .content(findArticleById.get().getContent())
                    .createdAt(findArticleById.get().getCreatedAt())
                    .boardId(findArticleById.get().getBoard().getId())
                    .memberId(findArticleById.get().getMember().getId())
                    .build();
        }
    }

    @ApiOperation(value = "Delete article")
    @DeleteMapping("/articles/{article_id}")
    public void deleteBoard(@PathVariable("article_id") Long articleId) {
        Optional<ArticleEntity> findArticleById = articleService.getById(articleId);
        if (findArticleById.isEmpty()) {
            throw new NotExist("article not exist");
        } else {
            articleService.remove(findArticleById.get());
        }
    }

}
