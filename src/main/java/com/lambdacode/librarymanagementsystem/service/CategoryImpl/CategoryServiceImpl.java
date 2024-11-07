package com.lambdacode.librarymanagementsystem.service.CategoryImpl;

import com.lambdacode.librarymanagementsystem.model.Category;
import com.lambdacode.librarymanagementsystem.repository.CategoryRepository;
import com.lambdacode.librarymanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
}
