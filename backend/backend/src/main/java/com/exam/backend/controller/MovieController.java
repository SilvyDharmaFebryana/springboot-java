package com.exam.backend.controller;

import java.util.List;
import java.util.Optional;

import com.exam.backend.dao.CategoryRepo;
import com.exam.backend.dao.MovieRepo;
import com.exam.backend.entity.Category;
import com.exam.backend.entity.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieRepo.save(movie);
    }

    @GetMapping
    public Iterable<Movie> getMovie() {
        return movieRepo.findAll();
    }

    @PostMapping("/{movieId}/category/{categoryId}") // add category di movie
    public Movie addCategoryToMovie(@PathVariable int movieId, @PathVariable int categoryId) {
        Movie findMovie = movieRepo.findById(movieId).get();

        Category findCategory = categoryRepo.findById(categoryId).get();

        findMovie.getCategory().add(findCategory);

        return movieRepo.save(findMovie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {

        Movie findMovie = movieRepo.findById(id).get();

        findMovie.getCategory().forEach(category -> {
            List<Movie> movieCategory = category.getMovie();
            movieCategory.remove(findMovie);
            categoryRepo.save(category);
        });

        findMovie.setCategory(null);
        movieRepo.deleteById(id);

    }

    @PutMapping
    public Movie updateMovie(@RequestBody Movie movie) {

        Optional<Movie> findMovie = movieRepo.findById(movie.getId());
        
        if (findMovie.toString() == "Optional.empty") {
            throw new RuntimeException("not found");
        }
        // findMovie.setId(0);
        
        return movieRepo.save(movie);

    }

    

}