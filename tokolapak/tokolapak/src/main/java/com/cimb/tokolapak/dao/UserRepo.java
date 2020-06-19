package com.cimb.tokolapak.dao;

import java.util.Optional;

import com.cimb.tokolapak.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);


}