package com.exam.backend.controller;

import java.util.List;

import com.exam.backend.dao.CategoryRepo;
import com.exam.backend.dao.MovieRepo;
import com.exam.backend.entity.Category;
import com.exam.backend.entity.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private MovieRepo movieRepo;

    @GetMapping
    public Iterable<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @GetMapping("/{categoryId}")
    public Category getProjectById(@PathVariable int categoryId) {
        Category findCategory = categoryRepo.findById(categoryId).get();

        if (findCategory == null)
            throw new RuntimeException("not found");
        
            return findCategory;
    }

    @GetMapping("/{categoryId}/movies")
    public List<Movie> getMoviesOnCategory(@PathVariable int categoryId){
        Category findCategory = categoryRepo.findById(categoryId).get();


        return findCategory.getMovie();
    }

    @PostMapping
    public Category addProject(@RequestBody Category category) {
        return categoryRepo.save(category);
    }


    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId) {
        Category findCategory =  categoryRepo.findById(categoryId).get();

        findCategory.getMovie().forEach(movies -> {
            List<Category> movieCategory = movies.getCategory();
            movieCategory.remove(findCategory);
            movieRepo.save(movies);
        });

        findCategory.setMovie(null);

        categoryRepo.deleteById(categoryId);

    }



    
}