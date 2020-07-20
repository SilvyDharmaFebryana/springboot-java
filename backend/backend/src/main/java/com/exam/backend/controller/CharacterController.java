package com.exam.backend.controller;

import com.exam.backend.dao.KarakterRepo;
import com.exam.backend.dao.MovieRepo;
// import com.exam.backend.dao.MovieRepo;
import com.exam.backend.entity.Karakter;
import com.exam.backend.entity.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/karakter")
public class CharacterController {

    @Autowired
    private KarakterRepo karakterRepo;

    @Autowired
    private MovieRepo movieRepo;

    // @Autowired
    // private MovieRepo movieRepo;

    @GetMapping
    public Iterable<Karakter> getCharacters() {
        return karakterRepo.findAll();
    }

    @PostMapping
    public Karakter addCharacters(@RequestBody Karakter karakter) {
        return karakterRepo.save(karakter);
    }

    @PutMapping("/{karakterId}/movie/{movieId}")
    public Karakter addMovieToKarakter(@PathVariable int movieId, @PathVariable int karakterId) {
        Karakter findKarakter = karakterRepo.findById(karakterId).get(); //employee yang id nya kita kirim

        if ( findKarakter == null)
            throw new RuntimeException("Karakter not found");

        
        Movie findMovie = movieRepo.findById(movieId).get();

        if (findMovie ==  null) 
            throw new RuntimeException("Department not found");

        
        findKarakter.setMovies(findMovie);
        return karakterRepo.save(findKarakter);
    }

    


        
}