package com.romashkako.myproducts.database.repository;

import com.romashkako.myproducts.database.entity.Product;
import com.romashkako.myproducts.database.entity.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
    @Query
    List<ProductSale> findAllByProduct(Product product);
}
