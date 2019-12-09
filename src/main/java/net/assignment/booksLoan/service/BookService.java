package net.assignment.booksLoan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.assignment.booksLoan.model.Book;
import net.assignment.booksLoan.repository.BookRepository;
 
@Service
@Transactional
public class BookService {
 
    @Autowired
    private BookRepository repo;
     
    public List<Book> listAll() {
        return repo.findAll();
    }
     
    public void save(Book product) {
        repo.save(product);
    }
     
    public Book get(long isbn) {
        return repo.findById(isbn).get();
    }
     
    public void delete(long isbn) {
        repo.deleteById(isbn);
    }
}