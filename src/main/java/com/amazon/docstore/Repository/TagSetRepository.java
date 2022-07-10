package com.amazon.docstore.Repository;

import com.amazon.docstore.models.TagSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagSetRepository extends MongoRepository<TagSet,Integer> {

}
