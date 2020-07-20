package com.exam.backend.dao;

import com.exam.backend.entity.Karakter;

import org.springframework.data.jpa.repository.JpaRepository; 

public interface KarakterRepo extends JpaRepository<Karakter, Integer>{
    


}