package com.amazon.docstore.services;


import com.amazon.docstore.models.Article;

import java.util.List;

public interface ArticleServices {
    Article getArticleById(int articleId);
    List<Article> getAllArticles();
    Article insertArticle(Article article);
    Article updateArticle(Article article, int articleId);
    List<Article> searchArticle(String query, List<String> tags);
    List<Article> searchArticleByTags(List<String> tags);
}
