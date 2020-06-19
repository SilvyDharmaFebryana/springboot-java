package com.cimb.tokolapak.dao;

import com.cimb.tokolapak.entity.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends CrudRepository<Product, Integer>{
    
    public Product findByProductName(String productName); 

    //Indexed parameter
    @Query(value = "SELECT * FROM Product WHERE price > ?1 and product_name = ?2", nativeQuery = true)
    public Iterable<Product> findProductsByMinPrice(double minPrice, String productName);

    // Named parameter
    @Query(value = "SELECT * FROM Product WHERE price < :maxPrice and product_name like %:productName%", nativeQuery = true)
    public Iterable<Product> findProductsByMaxPrice(@Param("maxPrice") double maxPrice, @Param("productName") String namaProduk);

}