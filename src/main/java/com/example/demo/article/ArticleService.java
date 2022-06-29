package com.example.demo.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleEntity create(ArticleEntity article) {
        return articleRepository.save(article);
    }

    public Optional<ArticleEntity> getById(Long id) {
        return articleRepository.findById(id);
    }

    public void remove(ArticleEntity article) {
        articleRepository.delete(article);
    }

}
