package net.assignment.booksLoan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.assignment.booksLoan.model.Autore;
import net.assignment.booksLoan.model.Libro;
import net.assignment.booksLoan.repository.LibroRepository;

@Service
@Transactional
public class LibroService {

    @Autowired
    private LibroRepository repo;

    public List<Libro> listAll() {
        return repo.findAll();
    }

    public void save(Libro libro) {
        repo.save(libro);
    }

    public Libro get(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

	public List<Libro> trovaSequel(int id) {
		return repo.trovaSequel(id);
	}
   
}