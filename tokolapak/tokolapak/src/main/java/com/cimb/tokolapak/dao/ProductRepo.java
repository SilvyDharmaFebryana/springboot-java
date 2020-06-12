package com.cimb.tokolapak.dao;

import com.cimb.tokolapak.entity.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Integer>{
    
    public Product findByProductName(String productName); 

}