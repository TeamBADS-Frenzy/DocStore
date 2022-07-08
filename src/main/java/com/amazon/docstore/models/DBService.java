package com.amazon.docstore.models;

import java.util.List;
import java.util.Set;

public interface DBService {
    Set<String> getAllTags();
    List<Article> getFilteredArticles(List<String> queryTags);

}
