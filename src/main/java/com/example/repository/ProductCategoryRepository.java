package com.example.repository;


import com.example.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    // Some category
    List<ProductCategory> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);

    // All category
    List<ProductCategory> findAllByOrderByCategoryType();

    // One category
    ProductCategory findByCategoryType(Integer categoryType);
}
