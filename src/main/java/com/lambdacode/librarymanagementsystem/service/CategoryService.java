package com.lambdacode.librarymanagementsystem.service;

import com.lambdacode.librarymanagementsystem.model.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    Category getCategoryById(Category category);
    List<Category> getAllCategories();

    void deleteCategory(Category category);
}
