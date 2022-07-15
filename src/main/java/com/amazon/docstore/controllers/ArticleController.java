package com.amazon.docstore.controllers;

import com.amazon.docstore.models.Article;
import com.amazon.docstore.models.SearchArticle;
import com.amazon.docstore.service.ArticleService;
import com.amazon.docstore.validator.ArticleValidator;
import com.amazon.docstore.validator.SearchArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000") // This should go into a web config. Hardcoding for now
@RequestMapping("/api/article")
public class ArticleController {

    private ArticleService articleService;
    private ArticleValidator articleValidator;
    private SearchArticleValidator searchArticleValidator;

    @Autowired
    public ArticleController(final ArticleService articleService,final ArticleValidator articleValidator, final SearchArticleValidator searchArticleValidator) {
        this.articleService = articleService;
        this.articleValidator = articleValidator;
        this.searchArticleValidator = searchArticleValidator;
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<Article> getArticleById(@PathVariable("articleId") String articleId) {
        Article responseArticle = articleService.getArticleById(UUID.fromString(articleId));
        return new ResponseEntity<>(responseArticle, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Article> uploadArticle(@RequestBody Article article) {
        log.debug("Inside upload article {}",article);
        articleValidator.validate(article, null);
        Article response = articleService.insertArticle(article);
        log.info("Article added with articleId: "+article.getArticleId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<Article> updateArticle(@PathVariable("articleId") Integer articleId, @RequestBody Article article) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Article>> searchArticle(@RequestBody SearchArticle searchArticle) {
        searchArticleValidator.validate(searchArticle,null);
        List<Article> response = articleService.searchArticle(searchArticle);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Article>> getAllArticle() {
        List<Article> response = articleService.getAllArticles();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
