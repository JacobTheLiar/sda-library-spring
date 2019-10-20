package pl.jacob_the_liar.sda.springboot.library.model;


/**
 * @author: Jakub O.  [https://github.com/JacobTheLiar]
 * @date : 2019-10-20 11:04
 * *
 * @className: Book
 * *
 * *
 ******************************************************/


import java.time.LocalDate;
import java.util.Objects;

import static pl.jacob_the_liar.sda.springboot.library.util.GeneratorUtil.getNewId;


public class Book {
    
    
    private int id;
    private String author;
    private String title;
    
    private LocalDate rentedTill;
    
    
    public Book(String author, String title){
        this.id = getNewId();
        this.author = author;
        this.title = title;
        this.rentedTill = null;
    }
    
    
    @Override
    public String toString(){
        return "Book{" + "id=" + id + ", author='" + author + '\'' + ", title='" + title + '\'' + ", insertDate=" + rentedTill + '}';
    }
    
    
    public int getId(){
        return id;
    }
    
    
    public String getAuthor(){
        return author;
    }
    
    
    public void setAuthor(String author){
        this.author = author;
    }
    
    
    public String getTitle(){
        return title;
    }
    
    
    public void setTitle(String title){
        this.title = title;
    }
    
    
    public LocalDate getRentedTill(){
        return rentedTill;
    }
    
    
    public void setRentedTill(LocalDate rentedTill){
        this.rentedTill = rentedTill;
    }
    
    
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }
    
    
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
