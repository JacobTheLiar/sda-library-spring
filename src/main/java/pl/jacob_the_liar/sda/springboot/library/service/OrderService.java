package pl.jacob_the_liar.sda.springboot.library.service;


import org.springframework.stereotype.Service;
import pl.jacob_the_liar.sda.springboot.library.model.Book;
import pl.jacob_the_liar.sda.springboot.library.repository.BookRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2019-10-20 11:08
 * *
 * @className: OrderService
 * *
 * *
 ******************************************************/
@Service
public class OrderService{
    
    private final BookRepository bookRepository;
    
    
    public OrderService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
        
    }
    
    
    public Optional<Book> borrowBook(String title){
        LocalDate borrowerTill = LocalDate.now().plusDays(30);
        return bookRepository.borrowBook(title, borrowerTill);
    }
    
    
    public Book addBook(String author, String title){
        Book newBook = new Book(author, title);
        return bookRepository.addNewBook(newBook);
    }
    
    
    public boolean removeBook(int bookId){
        return bookRepository.removeBook(bookId);
    }
    
    public void returnBook(int bookId){
        bookRepository.deliveryBook(bookId);
    }
    
    public Set<Book> allBooks(){
        return bookRepository.allBooks();
    }
    
    
    public Set<Book> findBook(String bookTitle, String bookAuthor){
        return bookRepository.findBook(bookTitle, bookAuthor);
    }
    
    
    public Optional<Book> borrowBookById(int id){
        LocalDate borrowerTill = LocalDate.now().plusDays(30);
        return bookRepository.borrowBookById(id, borrowerTill);
    }
    
    
    public Book addBook(Book book){
        return bookRepository.addNewBook(book);
    }

    public Optional<Book> findBookById(int id) {
        return bookRepository.allBooks().stream().filter(findBookById(id));
    }
}
