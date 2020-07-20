package com.exam.backend.dao;

import com.exam.backend.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    
    public void deleteById(Category category);

}