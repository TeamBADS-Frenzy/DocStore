package com.amazon.docstore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "TagSet")
public class TagSet {
    @Id
    private int id;
    private Set<String> allTags;
}
