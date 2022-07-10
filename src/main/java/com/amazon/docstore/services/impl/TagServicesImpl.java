package com.amazon.docstore.services.impl;

import com.amazon.docstore.Repository.TagSetRepository;
import com.amazon.docstore.models.TagSet;
import com.amazon.docstore.services.TagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServicesImpl implements TagServices {

    @Autowired
    private TagSetRepository tagSetRepository;

    @Override
    public TagSet getAllTags() {
        return tagSetRepository.findById(111).get();
    }

    @Override
    public void updateTagSet(TagSet T) {
        T.setId(111);
        tagSetRepository.save(T);
    }
}
