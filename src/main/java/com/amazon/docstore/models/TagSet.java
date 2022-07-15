package com.amazon.docstore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "TagSet")
public class TagSet {

    @Id
    private UUID id;
    private Set<String> allTags;

    public TagSet(final Set<String> allTags) {
        this.allTags = allTags;
    }

}
