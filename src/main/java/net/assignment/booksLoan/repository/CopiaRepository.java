package net.assignment.booksLoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.assignment.booksLoan.model.Copia;

@Repository
public interface CopiaRepository extends JpaRepository<Copia, Integer> {

	// https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-introduction-to-query-methods/
	@Query(value = "SELECT * FROM Copia c JOIN Libro l on c.id = l.id where c.id = ?1", nativeQuery = true)
	public List<Copia> findCopieById(int id);

	@Query(value = "SELECT DISTINCT l.titolo FROM Copia c JOIN Libro l on c.id = l.id where c.id = ?1", nativeQuery = true)
	public String findTitoloById(int id);

	@Modifying
	@Query(value = "UPDATE Copia SET disponibilita = 0 WHERE isbn = ?1", nativeQuery = true)
	public void setCopiaPrenotata(Long isbn);
	
	@Modifying
	@Query(value = "INSERT INTO Prestito (n_tessera, isbn) VALUES (?1, ?2)", nativeQuery = true)
	public void setUtentePrestito(int n_tessera, Long isbn);
	
	@Query(value = "SELECT u.n_tessera FROM Utente u WHERE u.username = ?1", nativeQuery = true)
	public int getN_tessera(String username);
	
	
}