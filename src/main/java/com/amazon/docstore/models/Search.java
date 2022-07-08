package com.amazon.docstore.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Getter
@Setter
public class Search {
    private String query;
    private List<String> queryTags;
    private Set<String> allTags;

    //@Autowired
    private DBService dbService;

    public Search(String query, List<String> queryTags) {
        this.query = query;
        this.queryTags = queryTags;
        this.allTags = dbService.getAllTags();
    }

    public Search(List<String> queryTags) {
        this.queryTags = queryTags;
        this.allTags = dbService.getAllTags();
    }

    public List<Article> getAllSearchedArticlesByLabels() {
        List<Article> searchedArticles = dbService.getFilteredArticles(this.queryTags);
        return searchedArticles;
    }

    public List<Article> getAllSearchedArticlesByQueryAndLabels() {
        List<Article> searchedArticles = dbService.getFilteredArticles(this.queryTags);
        Set<String> queryWords = new HashSet<>(Arrays.asList(this.query.split(" ")));
        List<Article> requiredArticles = new ArrayList<Article>();
        for (Article article : searchedArticles) {
            if (this.isValidTitle(article.getTitle(),queryWords)) {
                requiredArticles.add(article);
            }
        }
        return searchedArticles;
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