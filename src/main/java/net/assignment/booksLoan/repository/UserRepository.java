package net.assignment.booksLoan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.assignment.booksLoan.model.Utente;

@Repository
public interface UserRepository extends CrudRepository<Utente, Long>  {
    public Optional<Utente> findByUsername(String username);
    
    //https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-introduction-to-query-methods/
    @Query(value = "SELECT * FROM Utente t where t.n_tessera = ?1", nativeQuery = true) 
    public Optional<Utente> findUserByN_tessera(String n_tessera);
    

}