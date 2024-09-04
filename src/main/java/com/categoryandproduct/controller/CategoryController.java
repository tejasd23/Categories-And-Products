package com.categoryandproduct.controller;

import com.categoryandproduct.entity.Category;
import com.categoryandproduct.entity.Product;
import com.categoryandproduct.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryService.getAllCategories(pageable);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        return categoryService.updateCategory(id, categoryDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{id}/products")
    public Category addProductToCategory(@PathVariable Long id, @RequestBody Product product) {
        return categoryService.addProductToCategory(id, product);
    }

    @GetMapping("/{id}/products")
    public List<Product> getProductsByCategory(@PathVariable Long id) {
        return categoryService.getProductsByCategory(id);
    }
}
