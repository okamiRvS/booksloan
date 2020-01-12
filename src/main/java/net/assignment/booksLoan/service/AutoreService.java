package net.assignment.booksLoan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.assignment.booksLoan.model.Autore;
import net.assignment.booksLoan.repository.AutoreRepository;

@Service
@Transactional
public class AutoreService {

    @Autowired
    private AutoreRepository repo;

	public List<Autore> trovaAutoreScritto(int id) {
		return repo.trovaAutoreScritto(id);
	}

	public void setAutoreScritto(Autore a, int id) {
		repo.setScritto(id, saveAndFlush(a).getId_autore());
	}
	
	public void setAutoreSoloSuScritto(int id, int id_autore) {
		repo.setScritto(id, id_autore);
	}
	
	public Autore saveAndFlush(Autore a) {
		return repo.saveAndFlush(a);
	}

	public Autore getOne(int id_autore) {
		return repo.getOne(id_autore);
	}

	public Autore save(Autore autore) {
		return repo.save(autore);
		
	}

	public List<Autore> trovaAutoriDiversi(int id) {
		return repo.trovaAutoriDiversi(id);
	}
	
	public void deleteScritto(int id, int id_autore) {
        repo.deleteScritto(id, id_autore);
    }
	
}
