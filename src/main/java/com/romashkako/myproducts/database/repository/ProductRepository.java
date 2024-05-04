package com.romashkako.myproducts.database.repository;

import com.romashkako.myproducts.database.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM products WHERE name ILIKE :name", nativeQuery = true)
    List<Product> findProductsByName(@Param("name") String name);

    @Query(value = "SELECT * FROM products WHERE :priceFrom<price AND price<:priceTo", nativeQuery = true)
    List<Product> findProductsByPrice(@Param("priceFrom") Double priceFrom, @Param("priceTo") Double priceTo);

    @Query
    List<Product> findAllByInStock(Boolean inStock);




}