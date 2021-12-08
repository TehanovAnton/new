package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    @PostMapping
    public Book create(@RequestBody Book book) {
        return new Book();
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/json")
    public List<Book> firstPage() {
        return new ArrayList<Book>();
    }

    @DeleteMapping(path = { "/{id}" })
    public Book delete(@PathVariable("id") int id) {
        return new Book();
    }
}
