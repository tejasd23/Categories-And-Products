package com.categoryandproduct.service;

import com.categoryandproduct.entity.Category;
import com.categoryandproduct.entity.Product;
import com.categoryandproduct.repository.CategoryRepository;
import com.categoryandproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = getCategoryById(id);
        category.setName(categoryDetails.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category addProductToCategory(Long categoryId, Product product) {
        Category category = getCategoryById(categoryId);
        category.addProduct(product);
        return categoryRepository.save(category);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        Category category = getCategoryById(categoryId);
        return category.getProducts();
    }
}
