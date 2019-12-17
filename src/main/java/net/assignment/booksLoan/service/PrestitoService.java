package net.assignment.booksLoan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.assignment.booksLoan.model.Libro;
import net.assignment.booksLoan.model.Prestito;
import net.assignment.booksLoan.repository.PrestitoRepository;

@Service
@Transactional
public class PrestitoService {
	
	 @Autowired
	 private PrestitoRepository repo;
	 
	 public List<Prestito> listAll() {
	        return repo.findAll();
	  }

	 public List<Prestito> ElencoPrestiti(int n_tessera){
	        return repo.prenotazioni(n_tessera);
	  }
	    
}
