package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.domain.Article;
import com.example.demo.model.repository.BlogRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
    @Autowired
    private final BlogRepository blogRepository;

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public Optional<Article> findById(long id) {
        return blogRepository.findById(id);
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Optional<Article> update(long id, UpdateArticleRequest request) {
        Optional<Article> optionalArticle = blogRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.update(request.getTitle(), request.getContent());
        }
        return optionalArticle;
    }
}
