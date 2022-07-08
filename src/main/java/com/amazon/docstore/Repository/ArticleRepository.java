package com.amazon.docstore.Repository;

import com.amazon.docstore.models.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article,Integer> {

}
