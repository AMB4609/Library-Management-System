package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.model.Publisher;
import com.lambdacode.librarymanagementsystem.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;
    @PostMapping("/addPublisher")
    public ResponseEntity<String> addPublisher(@RequestBody Publisher publisher) {
        try {
            publisherService.addPublisher(publisher);
            return ResponseEntity.ok().build();
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
