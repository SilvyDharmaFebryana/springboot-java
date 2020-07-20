package com.cimb.tokolapak.controller;

import java.util.Optional;

import com.cimb.tokolapak.dao.UserRepo;
import com.cimb.tokolapak.entity.User;
import com.cimb.tokolapak.util.EmailUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepo userRepo;

    private PasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    @Autowired
    private EmailUtil emailUtil;

  
    @GetMapping("/{username}")
    public Optional<User> getUsername(@PathVariable String username) {
        return userRepo.findByUsername(username);
    }

    
    @GetMapping("/verifiedMail/{username}")
    public String verifiedMail(@PathVariable String username,  @RequestParam String token) {

        User findUser = userRepo.findByUsername(username).get();

        if (findUser.getPassword().equals(token)) {
            findUser.setVerified(true);
        } else {
            throw new RuntimeException("token is invalid");
        }
        userRepo.save(findUser);
        return "<h1> <center> Dear " + findUser.getUsername() + ", Akun anda telah terverifikasi </center> </h1>";
    }

    
    @PostMapping
    public User registeUser(@RequestBody User user) {
        // Optional<User> findUser = userRepo.findByUsername(user.getUsername());
        
        // if (findUser.toString() != "Optional.empty") {
        //     throw new RuntimeException("username exist");
        // }

        String encodedPassword = pwEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        
        emailUtil.sendEmail(user.getEmail(), "Registrasi Akun Sukses",
                "<h1> Sukses Buat Akun ! </h1> \n mohon klik <a href=\"http://localhost:8080/users/verifiedMail?username=" + user.getUsername() + "\">link</a> untuk memverifikasi akun anda");
        
        User savedUser = userRepo.save(user);
        savedUser.setPassword(null);

        return savedUser;

    //    "<a href=\"" + l + "\">" + l + "</a>\n"
    }

    

    @PostMapping("/login")
    public User loginUser (@RequestBody User user) {

        User findUser = userRepo.findByUsername(user.getUsername()).get();

                                // Password asli , Password encode
        if (pwEncoder.matches(user.getPassword(), findUser.getPassword())) {
            findUser.setPassword(null);
            return findUser;
        } 

        throw new RuntimeException("wrong password");

        // return null;
    }
    

    //localhost:8080/users/login?username=lala&password=lala
    @GetMapping("/login")
    public User getLoginUser(@RequestParam String username, @RequestParam String password) {
        
        User findUser = userRepo.findByUsername(username).get();

                                // Password asli , Password encode
        if (pwEncoder.matches(password, findUser.getPassword())) {
            findUser.setPassword(null);
            return findUser;
        } 

        throw new RuntimeException("wrong password");
    }


    @PostMapping("/sendEmail")  
    public String sendEmailTesting() {
        this.emailUtil.sendEmail("silvydharmafebryana@gmail.com", "TESTING SPRING-MAIL SILVY", "<h1>Hallo!! ini testing backend</h1, \nkalo kamu terima email ini.. berarti testingnya berhasil berhasil hore !!");
        return "Email sent!";
    }
    

}