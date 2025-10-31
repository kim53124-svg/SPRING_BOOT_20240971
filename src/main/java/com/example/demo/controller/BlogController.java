package com.example.demo.controller;

import com.example.demo.model.domain.Article;
import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;
import com.example.demo.model.service.UpdateArticleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/article_list")
    public String article_list(Model model) {
        List<Article> list = blogService.findAll();
        model.addAttribute("articles", list);
        return "article_list";
    }

    @PostMapping("/api/articles")
    public String addArticle(AddArticleRequest request) {
        blogService.save(request);
        return "redirect:/article_list";
    }

    @GetMapping("/article_edit/{id}")
    public String showEditArticleForm(@PathVariable long id, Model model) {
        Optional<Article> optionalArticle = blogService.findById(id);
        if (optionalArticle.isPresent()) {
            model.addAttribute("article", optionalArticle.get());
            return "article_edit";
        } else {
            return "error/404";
        }
    }

    @PutMapping("/api/article_edit/{id}")
    public String updateArticle(@PathVariable long id, UpdateArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/article_list";
    }

    @DeleteMapping("/api/article_delete/{id}")
    public String deleteArticle(@PathVariable long id) {
        blogService.delete(id);
        return "redirect:/article_list";
    }
}