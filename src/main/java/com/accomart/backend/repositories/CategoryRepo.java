package com.accomart.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.accomart.backend.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>  {
    Category findByName(String name);
}
