package net.assignment.booksLoan.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.assignment.booksLoan.model.Utente;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Long>  {

    //https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-introduction-to-query-methods/
    @Query(value = "SELECT * FROM Utente t where t.n_tessera = ?1", nativeQuery = true)
    public Utente findUserByN_tessera(String n_tessera);

    @Query(value = "SELECT * FROM Utente u where u.n_tessera = ?1", nativeQuery = true)
    public Utente findAdmByN_tessera(int n_tessera);

}