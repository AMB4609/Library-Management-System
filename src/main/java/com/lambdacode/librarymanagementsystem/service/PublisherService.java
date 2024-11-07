package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.model.Publisher;
import org.springframework.stereotype.Service;

@Service
public interface PublisherService {
    void addPublisher(Publisher publisher);
}
