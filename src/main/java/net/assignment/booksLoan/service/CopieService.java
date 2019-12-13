package net.assignment.booksLoan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.assignment.booksLoan.model.Copia;
import net.assignment.booksLoan.repository.CopiaRepository;

@Service
@Transactional
public class CopieService {

    @Autowired
    private CopiaRepository repo;

    public List<Copia> CopieId(int id){
        return repo.findCopieById(id);
    }

    public String TitoloId(int id){
        return repo.findTitoloById(id);
    }

    public void prenota(Copia copia) {
        repo.save(copia);
    }

    public Copia get(int id) {
        return repo.findById(id).get();
    }

}
