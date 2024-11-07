package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.model.Category;
import com.lambdacode.librarymanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/addCategory")
    public ResponseEntity<Void> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.ok().build();

    }
}
