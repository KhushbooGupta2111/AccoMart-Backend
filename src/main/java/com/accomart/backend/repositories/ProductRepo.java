package com.accomart.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.accomart.backend.entities.Product;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByNameContaining(String keyword);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByCategoryIdOrderByField(int categoryId, String orderBy);
    List<Product> findByCategoryId(int categoryId);
}
