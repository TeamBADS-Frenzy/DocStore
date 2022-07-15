package com.amazon.docstore.service.impl;

import com.amazon.docstore.Repository.TagSetRepository;
import com.amazon.docstore.models.TagSet;
import com.amazon.docstore.service.TagService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagServicesImpl implements TagService {

    private TagSetRepository tagSetRepository;
    private static final UUID ID = UUID.fromString("e5247671-6677-458b-acb1-7b4e27bef427");

    @Autowired
    public TagServicesImpl(final TagSetRepository tagSetRepository) {
        this.tagSetRepository = tagSetRepository;
    }
    @Override
    public TagSet getAllTags() {
        Optional<TagSet> optTagSet = tagSetRepository.findById(ID);
        if(optTagSet.isPresent()) {
            return tagSetRepository.findById(ID).get();
        }
        return new TagSet(ID, new HashSet<>());
    }

    @Override
    public void updateTagSet(TagSet tagSet) {
        if(ObjectUtils.isEmpty(tagSet.getId())) {
            // Creating new object as setter method is not exposed
            tagSet = new TagSet(ID, tagSet.getAllTags());
        }
        tagSetRepository.save(tagSet);
    }
}
