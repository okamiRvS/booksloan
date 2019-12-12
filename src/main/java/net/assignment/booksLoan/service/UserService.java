package net.assignment.booksLoan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.assignment.booksLoan.model.Utente;
import net.assignment.booksLoan.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
    private UserRepository repo;

    public List<Utente> listAll() {
        return repo.findAll();
    }

    public void save(Utente user) {
        repo.save(user);
    }

    public Utente accedi(int id) {
    	return repo.findById(id).get();
    }
    
    public Utente get(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
