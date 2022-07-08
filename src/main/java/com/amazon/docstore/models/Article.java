package com.amazon.docstore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "Article")
public class Article{
    @Id
    private Integer articleId;
    private String title;
    private String content;
    private String author;
    private LocalDate dateCreated;
    private List<String> tags;
}
