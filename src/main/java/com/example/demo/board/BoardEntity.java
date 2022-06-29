package com.example.demo.board;


import com.example.demo.article.ArticleEntity;
import com.example.demo.board.dto.BoardRequestDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "boards")
@Entity
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(unique = true, length = 100)
    private String name;

    // relation
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<ArticleEntity> articles = new ArrayList<>();

    @Builder
    public BoardEntity(String name) {
        this.name = name;
    }

    public void update(BoardRequestDTO boardDTO){
        this.setName(boardDTO.getName());
    }
}


