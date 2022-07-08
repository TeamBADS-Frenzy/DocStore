package com.amazon.docstore.controllers;

import com.amazon.docstore.Repository.ArticleRepository;
import com.amazon.docstore.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/{articleId}")
    public ResponseEntity<Article> getArticleById(@PathVariable("articleId") Integer articleId){
        String test = "test";
        Optional<Article> responseArticle = articleRepository.findById(articleId);
        return new ResponseEntity<>(responseArticle.get(), HttpStatus.OK);
        //return null;
    }

    @PostMapping("/")
    public ResponseEntity<Article> uploadArticle(@RequestBody Article article){
        String test = "test";
        articleRepository.save(article);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<Article> updateArticle(@PathVariable("articleId") Integer articleId, @RequestBody Article article){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Article>> searchArticleByQuery(@RequestParam("query") String query){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
