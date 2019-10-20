package pl.jacob_the_liar.sda.springboot.library.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import pl.jacob_the_liar.sda.springboot.library.model.Book;
import pl.jacob_the_liar.sda.springboot.library.service.OrderService;

import java.util.Optional;
import java.util.Set;



@RestController
public class BookController{
    
    private OrderService orderService;
    
    
    public BookController(OrderService orderService){
        this.orderService = orderService;
    }
    
    
    @GetMapping(value = "/books")
    public Set<Book> findBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author){
        if (title == null && author == null){
            return orderService.allBooks();
        }
        return orderService.findBook(title, author);
    }
    
    
    @GetMapping(value = "/book/borrow/{id}")
    public ResponseEntity<Book> borrowBook(@PathVariable int id){
        Optional<Book> book = orderService.borrowBookById(id);

        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping(value = "/book/add")
    public ResponseEntity<Integer> addBook(@RequestBody Book book){
        Book newBook = orderService.addBook(book);

        return new ResponseEntity<>(newBook.getId(), HttpStatus.CREATED);
    }


    @DeleteMapping("/book/remove/{id}")
    public ResponseEntity<Integer> deleteBook(@PathVariable int id){

        if (orderService.removeBook(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Book> findIdOfBook(@RequestParam int id) {
        Optional<Book> book = orderService.findBookById(id);
        if ((book.isPresent())) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/book/return/{id}", produces ="application/json")
    public ResponseEntity<Book> returnBook(@PathVariable int id){
        orderService.returnBook(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
