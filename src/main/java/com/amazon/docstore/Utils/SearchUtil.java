package com.amazon.docstore.Utils;

import com.amazon.docstore.models.Article;
import com.amazon.docstore.services.ArticleServices;
import com.amazon.docstore.services.TagServices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Getter
@Setter

public class SearchUtil {
    public List<Article> getAllSearchedArticlesByLabels(List<String> userQueryTags,ArticleServices articleServices) {
        return articleServices.searchArticleByTags(userQueryTags);
    }

    public List<Article> getAllSearchedArticlesByQueryAndLabels(List<String> userQueryTags,String query,ArticleServices articleServices,TagServices tagServices) {
        List<Article> searchedArticles = articleServices.searchArticleByTags(userQueryTags);
        Set<String> allTags = tagServices.getAllTags().getAllTags();
        List<String> queryList = new ArrayList<>(Arrays.asList(query.split(" ")));
        queryList.removeIf(label -> !allTags.contains(label));
        Set<String> queryWords = new HashSet<>(queryList);
        queryWords.addAll(queryList);
        List<Article> requiredArticles = new ArrayList<Article>();
        for (Article article : searchedArticles) {
            if (this.isValidTitle(article.getTitle(),queryWords)) {
                requiredArticles.add(article);
            }
        }
        return requiredArticles;
    }

    private Boolean isValidTitle(String title,Set<String> queryWords){
        Set<String> titleWords = new HashSet<>();
        titleWords.addAll(Arrays.asList(title.split(" ")));
        for(String word : titleWords){
            if(queryWords.contains(word))
                return true;
        }

        return false;
    }

}