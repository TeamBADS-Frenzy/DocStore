package com.amazon.docstore.controllers;


import com.amazon.docstore.models.TagSet;
import com.amazon.docstore.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/tag")
public class TagsController {

    private TagService tagService;

    @Autowired
    public TagsController(final TagService tagService) {
        this.tagService = tagService;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTag(@RequestBody TagSet tagSet) {
        tagService.updateTagSet(tagSet);
        return ResponseEntity.ok("Updated");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllTags() {
        Set<String> response = tagService.getAllTags().getAllTags();
        return ResponseEntity.ok(response);
    }
}
