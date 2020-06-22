package com.cimb.tokolapak.controller;

import java.util.Optional;

import com.cimb.tokolapak.dao.UserRepo;
import com.cimb.tokolapak.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/verified")
public class VerifiedUserController {
    

    @Autowired
    private UserRepo userRepo;

    // @GetMapping("/{username}")
    // public Optional<User> getUsername(@PathVariable String username) {
    //     return userRepo.findByUsername(username);
    // }

    @PutMapping("/{username}/verifiedMail/")
    public User verifiedMail(@PathVariable String username) {
        User findUser = userRepo.findByUsername(username).get();

        findUser.setVerified(true);
        return userRepo.save(findUser);
    }

}