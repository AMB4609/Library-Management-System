package com.lambdacode.librarymanagementsystem.service.CategoryImpl;

import com.lambdacode.librarymanagementsystem.exception.NotFoundException;
import com.lambdacode.librarymanagementsystem.model.Category;
import com.lambdacode.librarymanagementsystem.repository.CategoryRepository;
import com.lambdacode.librarymanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        if (category == null || category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name must not be null or empty.");
        }
        categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Category category) {
        return categoryRepository.findById(category.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found."));
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }
}
