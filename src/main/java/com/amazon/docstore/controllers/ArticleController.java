package com.amazon.docstore.controllers;

import com.amazon.docstore.models.Article;
import com.amazon.docstore.Utils.SearchUtil;
import com.amazon.docstore.models.TagSet;
import com.amazon.docstore.services.ArticleServices;
import com.amazon.docstore.services.TagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleServices articleServices;

    @Autowired
    private TagServices tagServices;

    @GetMapping("/{articleId}")
    public ResponseEntity<Article> getArticleById(@PathVariable("articleId") Integer articleId){
        Article responseArticle = articleServices.getArticleById(articleId);
        return new ResponseEntity<>(responseArticle, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Article> uploadArticle(@RequestBody Article article){
        Set<String> allTags =  tagServices.getAllTags().getAllTags();
        allTags.addAll(article.getTags());
        tagServices.updateTagSet(new TagSet(111,allTags));
        return new ResponseEntity<>(articleServices.insertArticle(article), HttpStatus.OK);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<Article> updateArticle(@PathVariable("articleId") Integer articleId, @RequestBody Article article){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/search/query")
    public ResponseEntity<?> searchArticleByQuery(@RequestParam("query") String query, @RequestBody List<String> tags){
        SearchUtil searchUtil = new SearchUtil();
        List<Article> response = searchUtil.getAllSearchedArticlesByQueryAndLabels(tags,query);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Article>> searchArticleByTags(@RequestBody List<String> tags){
        SearchUtil searchUtil = new SearchUtil();
        List<Article> response = articleServices.searchArticleByTags(tags);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
