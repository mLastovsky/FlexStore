package com.mlastovsky.repository;

import com.mlastovsky.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("FROM Product p WHERE p.id IN :ids ORDER BY p.id")
    List<Product> findAllByIdIn(List<Long> ids);

}
