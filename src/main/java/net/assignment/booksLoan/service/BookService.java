package net.assignment.booksLoan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.assignment.booksLoan.model.Libro;
import net.assignment.booksLoan.repository.BookRepository;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository repo;

    public List<Libro> listAll() {
        return repo.findAll();
    }

    public void save(Libro product) {
        repo.save(product);
    }

    public Libro get(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}