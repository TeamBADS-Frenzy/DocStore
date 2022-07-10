package com.amazon.docstore.Repository;

import com.amazon.docstore.models.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Article,Integer> {

    @Query(value = "{tags : { $in: ?0 }}")
    List<Article> findByTags(List<String> tags);
}
