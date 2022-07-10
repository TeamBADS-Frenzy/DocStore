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

    @Autowired
    private TagServices tagServices;

    @Autowired
    private ArticleServices articleServices;



    public List<Article> getAllSearchedArticlesByLabels(List<String> userQueryTags) {
        return articleServices.searchArticleByTags(userQueryTags);
    }

    public List<Article> getAllSearchedArticlesByQueryAndLabels(List<String> queryTags,String query) {
        List<Article> searchedArticles = articleServices.searchArticleByTags(queryTags);
        List<String> queryList = Arrays.asList(query.split(" "));
        Set<String> allTags = tagServices.getAllTags().getAllTags();
        queryList.removeIf(b -> !allTags.contains(b));
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