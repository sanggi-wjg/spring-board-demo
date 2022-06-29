package com.example.demo.board;

import com.example.demo.board.dto.BoardRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Page<BoardEntity> listByPaged(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Optional<BoardEntity> getById(Long boardId) {
        return boardRepository.findById(boardId);
    }

    public BoardEntity create(BoardEntity boardEntity) {
        return boardRepository.save(boardEntity);
    }

    public void remove(BoardEntity boardEntity) {
        boardRepository.delete(boardEntity);
    }


}
