package com.library.demo;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class LibraryController {

    private List<Book> bookList = new ArrayList<>();

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome Sunny to the Online Library System!";
    }

    @GetMapping("/count")
    public int getCount() {
        return 250; 
    }

    @GetMapping("/price")
    public double getPrice() {
        return 750.0;
    }

    @GetMapping("/books")
    public List<String> getTitles() {
        return Arrays.asList("Core Java", "Spring Boot MVC", "ReactJS Essentials");
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable int id) {
        return "Displaying details for Book ID: " + id;
    }

    @GetMapping("/search")
    public String search(@RequestParam String title) {
        return "Confirmation: Searching for book title: " + title;
    }

    @GetMapping("/author/{name}")
    public String getAuthor(@PathVariable String name) {
        return "The requested author is: " + name;
    }

    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookList.add(book);
        return "Success! Book '" + book.getTitle() + "' has been added.";
    }

    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return bookList;
    }
}