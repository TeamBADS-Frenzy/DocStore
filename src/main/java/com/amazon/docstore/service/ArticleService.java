package com.amazon.docstore.service;


import com.amazon.docstore.models.Article;
import com.amazon.docstore.models.SearchArticle;

import java.util.List;
import java.util.UUID;

public interface ArticleService {
    Article getArticleById(UUID articleId);

    List<Article> getAllArticles();

    Article insertArticle(Article article);

    Article updateArticle(UUID articleId, Article article);

    List<Article> searchArticle(SearchArticle searchArticle);

    List<Article> searchArticleByTags(List<String> tags);
}
