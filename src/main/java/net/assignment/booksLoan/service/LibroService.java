package net.assignment.booksLoan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    	repo.deleteSequels(id);
    	repo.deleteScrittori(id);
        repo.deleteById(id);
    }

	public List<Libro> trovaSequel(int id) {
		return repo.trovaSequel(id);
	}

	public List<Libro> trovaLibriDiversi(int id) {
		return repo.trovaLibriDiversi(id);
	}
	public void setSequel(Libro libro, int id_libro) {
		repo.setSequel(id_libro, saveAndFlush(libro).getId());
	}

	public Libro saveAndFlush(Libro a) {
		return repo.saveAndFlush(a);
	}

	public void setLibroSoloSuSequel(int id, int id_2) {
		repo.setSequel(id, id_2);
	}

	public void deleteSequel(int id, int id_2) {
        repo.deleteSequel(id, id_2);
    }
	
	public void deleteSequels(int id) {
        repo.deleteSequels(id);
    }

	public Libro ricerca(String titolo) {
	    return repo.ricerca(titolo);
	}
	
}