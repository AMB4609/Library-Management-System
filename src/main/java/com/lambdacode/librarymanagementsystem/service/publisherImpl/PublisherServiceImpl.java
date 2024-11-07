package com.lambdacode.librarymanagementsystem.service.publisherImpl;

import com.lambdacode.librarymanagementsystem.model.Publisher;
import com.lambdacode.librarymanagementsystem.repository.PublisherRepository;
import com.lambdacode.librarymanagementsystem.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;
@Override
    public void addPublisher(Publisher publisher) {
    if (publisher.getPublisherName() == null) {
        throw new IllegalArgumentException("Publisher name must not be null");
    }
    publisherRepository.save(publisher);
    }
}
