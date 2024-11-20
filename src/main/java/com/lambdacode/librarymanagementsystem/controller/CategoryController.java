package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.model.Category;
import com.lambdacode.librarymanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/addCategory")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<Void> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getCategoryById")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<Category> getCategoryById(@RequestBody Category category) {
        Category categoryDetails = categoryService.getCategoryById(category);
        return ResponseEntity.ok().body(categoryDetails);
    }
    @GetMapping("/getAllCategories")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categoryList);
    }
    @DeleteMapping("/deleteCategory")
    @PreAuthorize("hasAnyAuthority('ROLE_LIBRARIAN','ROLE_ADMIN')")
    public ResponseEntity<String> deleteCategory(@RequestBody Category category) {
        categoryService.deleteCategory(category);
        return ResponseEntity.ok().body("Deleted Category");
    }
}
