package com.accomart.backend.services;

import com.accomart.backend.entities.Category;
import com.accomart.backend.entities.Product;
import com.accomart.backend.entities.dto.UpdateProduct;
import com.accomart.backend.entities.dto.ViewProduct;
import com.accomart.backend.repositories.CategoryRepo;
import com.accomart.backend.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;
    
    @Autowired
    private CategoryRepo categoryRepo;

    public CompletableFuture<Category> updateCategoryAsync(int id, String newCategoryName) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<Category> categoryOpt = categoryRepo.findById(id);
            if (categoryOpt.isPresent()) {
                Category category = categoryOpt.get();
                category.setCategoryName(newCategoryName);
                return categoryRepo.save(category);
            }
            return null;
        });
    }

    public CompletableFuture<Product> createProductAsync(ViewProduct productDto) {
        return CompletableFuture.supplyAsync(() -> {
            Product product = new Product();
            product.setProductName(productDto.getProductName());
            product.setProductDesc(productDto.getProductDesc());
            product.setProductPrice(productDto.getProductPrice());
            product.setProductImageUrl(productDto.getProductImageUrl());
            product.setCategoryId(productDto.getCategoryId());
            product.setStock(productDto.getStock());
            return productRepo.save(product);
        });
    }

    public CompletableFuture<List<Product>> getAllProductsByCategoryAsync(int id, String orderBy) {
        return CompletableFuture.supplyAsync(() -> productRepo.findByCategoryIdOrderByField(id, orderBy));
    }

    public CompletableFuture<List<Product>> getProductsByPageNoAsync(int id, int pageNo, int pageSize) {
        return CompletableFuture.supplyAsync(() -> {
            int offset = (pageNo - 1) * pageSize;
            return productRepo.findByCategoryId(id).subList(offset, offset + pageSize);
        });
    }

    public CompletableFuture<List<Product>> getAllProductsPagewiseAsync(int pageNo, int pageSize) {
        return CompletableFuture.supplyAsync(() -> {
            int offset = (pageNo - 1) * pageSize;
            return productRepo.findAll().subList(offset, offset + pageSize);
        });
    }

    public CompletableFuture<List<Product>> getProductBySearchNameAsync(String prefix) {
        return CompletableFuture.supplyAsync(() -> productRepo.findByNameContaining(prefix));
    }

    public CompletableFuture<List<Product>> getProductsByCategoryNameAsync(String name) {
        return CompletableFuture.supplyAsync(() -> {
            Category category = categoryRepo.findByName(name);
            return productRepo.findByCategoryId(category.getCategoryId());
        });
    }

    public CompletableFuture<Product> getProductByIdAsync(int id) {
        return CompletableFuture.supplyAsync(() -> productRepo.findById(id).orElse(null));
    }

    public CompletableFuture<List<Category>> getAllCategoriesAsync() {
        return CompletableFuture.supplyAsync(() -> categoryRepo.findAll());
    }

    public CompletableFuture<Category> getCategoryByIdAsync(int id) {
        return CompletableFuture.supplyAsync(() -> categoryRepo.findById(id).orElse(null));
    }

    public CompletableFuture<Category> getCategoryByNameAsync(String name) {
        return CompletableFuture.supplyAsync(() -> categoryRepo.findByName(name));
    }

    public CompletableFuture<List<Product>> getAllProducts() {
        return CompletableFuture.supplyAsync(() -> productRepo.findAll());
    }

    public CompletableFuture<Product> updateProductAsync(int productId, UpdateProduct productDto) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<Product> productOpt = productRepo.findById(productId);
            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                product.setProductName(productDto.getProductName());
                product.setProductDesc(productDto.getProductDesc());
                product.setProductPrice(productDto.getProductPrice());
                product.setProductImageUrl(productDto.getProductImageUrl());
                product.setCategoryId(productDto.getCategoryId());
                product.setStock(productDto.getStock());
                return productRepo.save(product);
            }
            return null;
        });
    }

    public CompletableFuture<Void> deleteCategoryAsync(int categoryId) {
        return CompletableFuture.runAsync(() -> categoryRepo.deleteById(categoryId));
    }

    public CompletableFuture<Void> deleteProductAsync(int productId) {
        return CompletableFuture.runAsync(() -> productRepo.deleteById(productId));
    }

    public CompletableFuture<Category> createCategoryAsync(String categoryName) {
        return CompletableFuture.supplyAsync(() -> {
            Category category = new Category();
            category.setCategoryName(categoryName);
            return categoryRepo.save(category);
        });
    }
}
