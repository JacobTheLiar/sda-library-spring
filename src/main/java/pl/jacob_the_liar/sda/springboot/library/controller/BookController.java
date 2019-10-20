package pl.jacob_the_liar.sda.springboot.library.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
    public Set<Book> findBooks(@RequestParam @Nullable String find){
        if (find == null) {
            return orderService.allBooks();
        }
        return orderService.findBook(find);
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

    @GetMapping(value = "/book/return/{bookId}", produces ="application/json")
    public ResponseEntity<Book> returnBook(@PathVariable int bookId){
        orderService.returnBook(bookId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
