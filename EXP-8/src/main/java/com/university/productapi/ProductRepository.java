package com.university.productapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Task 2a: Derived Query Method
    List<Product> findByCategory(String category);

    // Task 2b: Derived Query Method
    List<Product> findByPriceBetween(double min, double max);

    // Task 3a: JPQL for Sorting
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> getAllSortedByPrice();

    // Task 3b: JPQL for Price Filtering
    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> getExpensiveProducts(@Param("price") double price);

    // Task 3c: JPQL for Category Search
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> getProductsByCategory(@Param("category") String category);
}