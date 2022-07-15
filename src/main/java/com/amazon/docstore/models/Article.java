package com.amazon.docstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Document(collection = "Article")
public class Article {
    @Id
    private UUID articleId;
    private String title;
    private String content;
    private String author;
    private LocalDate dateCreated;
    private List<String> tags;
}
