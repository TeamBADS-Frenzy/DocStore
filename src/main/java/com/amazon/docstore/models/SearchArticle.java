package com.amazon.docstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchArticle {
    String query;
    List<String> tags;

    public String getQuery() {
        return query.toUpperCase();
    }
}
