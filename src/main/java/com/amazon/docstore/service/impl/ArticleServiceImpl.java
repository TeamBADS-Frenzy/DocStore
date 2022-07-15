package com.amazon.docstore.service.impl;

import com.amazon.docstore.Repository.ArticleRepository;
import com.amazon.docstore.utils.CommonUtil;
import com.amazon.docstore.exception.ResourceNotFoundException;
import com.amazon.docstore.models.Article;
import com.amazon.docstore.models.SearchArticle;
import com.amazon.docstore.models.TagSet;
import com.amazon.docstore.service.ArticleService;
import com.amazon.docstore.service.TagService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private TagService tagService;

    @Autowired
    public ArticleServiceImpl(final ArticleRepository articleRepository, final TagService tagService) {
        this.articleRepository = articleRepository;
        this.tagService = tagService;
    }

    @Override
    public Article getArticleById(final UUID articleId) {
        Article response = this.articleRepository.findById(articleId).get();
        return response;
    }

    @Override
    public List<Article> getAllArticles() {
        return this.articleRepository.findAll();
    }

    @Override
    public Article insertArticle(final Article article) {
        Set<String> allTags = tagService.getAllTags().getAllTags();
        allTags.addAll(article.getTags());
        tagService.updateTagSet(new TagSet(allTags));
        if (ObjectUtils.isEmpty(article.getArticleId())) {
            article.setArticleId(UUID.randomUUID());
        }
        articleRepository.save(article);
        return article;
    }

    @Override
    public Article updateArticle(final UUID articleId,final Article article) {
        // TODO : Add update logic
        return article;
    }

    @Override
    public List<Article> searchArticle(final SearchArticle searchArticle) {
        List<Article> searchedArticles =
                (!ObjectUtils.isEmpty(searchArticle.getTags()) ?
                        searchArticleByTags(searchArticle.getTags()): articleRepository.findAll());
        if (!StringUtils.isEmpty(searchArticle.getQuery())) {
            // To remove unwanted words like He, She, They, is etc. for starting we will consider on
            String optimizedQuery = CommonUtil.removeStopWords(searchArticle.getQuery());
            Set<String> distinctWords  = new HashSet<>();
            for(String query : optimizedQuery.split(" ")) {
                distinctWords.add(query.toUpperCase());
            }
            return filterAndSortArticles(searchedArticles, distinctWords);
        }
        if (searchedArticles.isEmpty()) {
            // No record found
            throw new ResourceNotFoundException("query:["+searchArticle.getQuery() + "] tags: ["+searchArticle.getTags()+"]");
        }
        return searchedArticles;
    }

    /**
     * Articles will be filtered based on if a single word is present from distinct words in title and
     * sorted based on number of words occured from distinct word descending
     * @param searchedArticles
     * @param distinctWords
     * @return
     */
    private List<Article> filterAndSortArticles(final List<Article> searchedArticles,final Set<String> distinctWords) {
        TreeMap<Integer,List<Article>> resultMap = new TreeMap<>(Comparator.reverseOrder());
        for(Article article : searchedArticles) {
            int count = CommonUtil.countOccurrences(article.getTitle().toUpperCase(), distinctWords);
            if(count > 0) {
                resultMap.computeIfAbsent(count, k -> new ArrayList<>());
                List<Article> articleLst = resultMap.get(count);
                articleLst.add(article);
            }
        }
        List<Article> result = new ArrayList<>();
        resultMap.values().stream().forEach(e -> result.addAll(e));
        return result;
    }

    @Override
    public List<Article> searchArticleByTags(final List<String> tags) {
        return this.articleRepository.findByTags(tags);
    }
}
