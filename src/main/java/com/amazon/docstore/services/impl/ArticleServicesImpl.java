package com.amazon.docstore.services.impl;

import com.amazon.docstore.Repository.ArticleRepository;
import com.amazon.docstore.models.Article;
import com.amazon.docstore.services.ArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServicesImpl implements ArticleServices {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article getArticleById(int articleId) {
        try
        {
            Article response = this.articleRepository.findById(articleId).get();
            return response;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Article> getAllArticles() {
        return this.articleRepository.findAll();
    }

    @Override
    public Article insertArticle(Article article) {
        this.articleRepository.save(article);
        return article;
    }

    @Override
    public Article updateArticle(Article article, int articleId) {
        article.setArticleId(articleId);
        this.articleRepository.save(article);
        return article;
    }

    @Override
    public List<Article> searchArticle(String query, List<String> tags) {
        return null;
    }

    @Override
    public List<Article> searchArticleByTags(List<String> tags) {
        return this.articleRepository.findByTags(tags);
    }
}
