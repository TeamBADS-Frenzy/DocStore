package com.amazon.docstore.models;

import com.amazon.docstore.services.ArticleServices;
import com.amazon.docstore.services.TagServices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Getter
@Setter

public class SearchUtil {
    private String query;
    private List<String> queryTags;
    private Set<String> allTags;

    @Autowired
    private TagServices tagServices;

    @Autowired
    private ArticleServices articleServices;

    public SearchUtil(String query, List<String> queryTags) {
        this.query = query;
        this.queryTags = queryTags;
        this.allTags = tagServices.getAllTags().getAllTags();
    }

    public SearchUtil(List<String> queryTags) {
        this.queryTags = queryTags;
    }

    public List<Article> getAllSearchedArticlesByLabels() {
        return articleServices.searchArticleByTags(this.queryTags);
    }

    public List<Article> getAllSearchedArticlesByQueryAndLabels() {
        List<Article> searchedArticles = articleServices.searchArticleByTags(this.queryTags);
        List<String> queryList = Arrays.asList(this.query.split(" "));
        queryList.removeIf(b -> !this.allTags.contains(b));
        Set<String> queryWords = new HashSet<>(queryList);
        List<Article> requiredArticles = new ArrayList<Article>();
        for (Article article : searchedArticles) {
            if (this.isValidTitle(article.getTitle(),queryWords)) {
                requiredArticles.add(article);
            }
        }
        return requiredArticles;
    }

    private Boolean isValidTitle(String title,Set<String> queryWords){
        Set<String> titleWords = new HashSet<>(Arrays.asList(title.split(" ")));
        for(String word : titleWords){
            if(queryWords.contains(word))
                return true;
        }

        return false;
    }

}