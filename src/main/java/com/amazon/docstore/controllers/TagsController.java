package com.amazon.docstore.controllers;


import com.amazon.docstore.models.Article;
import com.amazon.docstore.models.TagSet;
import com.amazon.docstore.services.TagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/tag")
public class TagsController {

    @Autowired
    private TagServices tagServices;

    @PutMapping("/update")
    public ResponseEntity<?> updateTag(@RequestBody TagSet T){
        T.setId(111);
        tagServices.updateTagSet(T);
        return ResponseEntity.ok("Updated");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllTags(@RequestBody TagSet T){
        Set<String> response =  tagServices.getAllTags().getAllTags();
        return ResponseEntity.ok(response);
    }




}
