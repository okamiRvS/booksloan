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

}
