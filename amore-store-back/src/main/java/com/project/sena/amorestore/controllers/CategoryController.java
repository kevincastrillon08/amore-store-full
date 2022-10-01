package com.project.sena.amorestore.controllers;

import com.project.sena.amorestore.models.Category;
import com.project.sena.amorestore.models.Product;
import com.project.sena.amorestore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class  CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category/all")
    public List<Category> getAll() {return categoryRepository.findAll(); }

    @PostMapping("/category")
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryRepository.save(category);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category, @PathVariable long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (!categoryOptional.isPresent())
            return ResponseEntity.notFound().build();

        Category savedCategory = categoryRepository.save(category);
        return ResponseEntity.ok(savedCategory);
    }

    @DeleteMapping("/category/{id}")
    public  void deleteCategory(@PathVariable long id) {categoryRepository.deleteById(id);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Object> findCategoryById( @PathVariable long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if(!categoryOptional.isPresent())
            return ResponseEntity.notFound().build();

        return  ResponseEntity.ok(categoryOptional.get());
    }

}


