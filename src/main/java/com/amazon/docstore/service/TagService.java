package com.amazon.docstore.service;

import com.amazon.docstore.models.TagSet;

public interface TagService {
    TagSet getAllTags();

    void updateTagSet(TagSet T);
}
