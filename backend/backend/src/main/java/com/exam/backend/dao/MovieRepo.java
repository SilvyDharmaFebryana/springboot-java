package com.exam.backend.dao;

import com.exam.backend.entity.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
    
}