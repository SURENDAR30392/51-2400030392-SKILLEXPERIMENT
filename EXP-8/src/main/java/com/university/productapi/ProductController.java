package com.university.productapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    // Helper method to add data (Task 5)
    @PostMapping("/add")
    public String add(@RequestBody Product p) {
        repository.save(p);
        return "Product Saved!";
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCat(@PathVariable String category) {
        return repository.findByCategory(category);
    }

    @GetMapping("/filter")
    public List<Product> filter(@RequestParam double min, @RequestParam double max) {
        return repository.findByPriceBetween(min, max);
    }

    @GetMapping("/sorted")
    public List<Product> getSorted() {
        return repository.getAllSortedByPrice();
    }

    @GetMapping("/expensive/{price}")
    public List<Product> getExp(@PathVariable double price) {
        return repository.getExpensiveProducts(price);
    }
}