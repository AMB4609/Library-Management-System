package com.lambdacode.librarymanagementsystem.controller;

import com.lambdacode.librarymanagementsystem.model.Category;
import com.lambdacode.librarymanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lambdacode.librarymanagementsystem.constant.AuthorizeConstant.HAS_ROLE_LIBRARIAN_OR_ADMIN;
import static com.lambdacode.librarymanagementsystem.constant.CategoryConstant.*;

@RestController
@RequestMapping(CATEGORY)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping( ADD_CATEGORY)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<Void> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GET_CATEGORY_BY_ID)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<Category> getCategoryById(@RequestBody Category category) {
        Category categoryDetails = categoryService.getCategoryById(category);
        return ResponseEntity.ok().body(categoryDetails);
    }
    @GetMapping(GET_ALL_CATEGORIES)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categoryList);
    }
    @DeleteMapping(DELETE_CATEGORY)
    @PreAuthorize(HAS_ROLE_LIBRARIAN_OR_ADMIN)
    public ResponseEntity<String> deleteCategory(@RequestBody Category category) {
        categoryService.deleteCategory(category);
        return ResponseEntity.ok().body("Deleted Category");
    }
}
