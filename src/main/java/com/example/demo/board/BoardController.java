package com.example.demo.board;

import com.example.demo.article.ArticleEntity;
import com.example.demo.board.dto.BoardRequestDTO;
import com.example.demo.common.exception.NotExist;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "Board")
@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Board list")
    @GetMapping("/boards")
    public Page<BoardEntity> boardList(Pageable pageable) {
        return boardService.listByPaged(pageable);
    }

    @ApiOperation(value = "Board one")
    @GetMapping("/boards/{board_id}")
    public BoardEntity board(@PathVariable("board_id") Long boardId) {
        Optional<BoardEntity> boardById = boardService.getById(boardId);
        if (boardById.isEmpty()) {
            throw new NotExist("board not exist");
        } else {
            return boardById.get();
        }
    }

    @ApiOperation(value = "Board articles")
    @GetMapping("/boards/{board_id}/articles")
    public List<ArticleEntity> boardArticles(@PathVariable("board_id") Long boardId) {
        Optional<BoardEntity> boardById = boardService.getById(boardId);
        if (boardById.isEmpty()) {
            throw new NotExist("board not exist");
        } else {
            return boardById.get().getArticles();
        }
    }

    @ApiOperation(value = "Create board")
    @PostMapping("/boards")
    public BoardEntity createBoard(@Valid @RequestBody BoardRequestDTO boardRequestDTO) {
        return boardService.create(modelMapper.map(boardRequestDTO, BoardEntity.class));
    }


    @ApiOperation(value = "Update board")
    @PutMapping("/boards/{board_id}")
    public BoardEntity updateBoard(@PathVariable("board_id") Long boardId, @Valid @RequestBody BoardRequestDTO boardDTO) {
        Optional<BoardEntity> boardById = boardService.getById(boardId);
        if (boardById.isEmpty()) {
            throw new NotExist("board not exist");
        } else {
            boardById.get().update(boardDTO);
            return boardById.get();
        }
    }

    @ApiOperation(value = "Delete board")
    @DeleteMapping("/boards/{board_id}")
    public void deleteBoard(@PathVariable("board_id") Long boardId) {
        Optional<BoardEntity> boardById = boardService.getById(boardId);
        if (boardById.isEmpty()) {
            throw new NotExist("board not exist");
        } else {
            boardService.remove(boardById.get());
        }
    }


}
