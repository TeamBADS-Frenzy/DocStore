package com.amazon.docstore.services;

import com.amazon.docstore.models.TagSet;

public interface TagServices{
    TagSet getAllTags();
    void updateTagSet(TagSet T);
}
