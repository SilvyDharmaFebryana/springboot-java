package com.cimb.tokolapak.controller;

import java.util.Optional;

import com.cimb.tokolapak.dao.ProductRepo;
import com.cimb.tokolapak.entity.Product;
import com.cimb.tokolapak.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    // alur pola backend 
    // Controller -> Service -> DAO / Repo -> DB

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductService productService;
    
    @GetMapping("/products")
    public Iterable<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public Product addProducts(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable() int id) {
		this.productService.deleteProductById(id);
	}

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping("/productName/{productName}")
    public Product getProductByProductName(@PathVariable String productName) {
        return productRepo.findByProductName(productName);
    }

    @GetMapping("/products/custom")
    public Iterable<Product> getCustomProducts(@RequestParam double minPrice, @RequestParam String productName) {
        return productRepo.findProductsByMinPrice(minPrice, productName);
    }

    @GetMapping("/products/query")
    public Iterable<Product> customQuery(@RequestParam double maxPrice, @RequestParam String namaProduk) {
        return productRepo.findProductsByMaxPrice(maxPrice, namaProduk);
    }
}