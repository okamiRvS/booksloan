package net.assignment.booksLoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.assignment.booksLoan.model.Autore;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, Integer> {
	@Query(value = "SELECT a.id_autore, a.nome, a.cognome FROM Autore a JOIN Scritto s on a.id_autore = s.id_autore where s.id = ?1", nativeQuery = true)
	public List<Autore> trovaAutoreScritto(int id);
	
	@Modifying
    @Query(value = "INSERT INTO Scritto (id, id_autore) VALUES (?1, ?2)", nativeQuery = true)
    public void setScritto(int id, int id_autore);

	@Query(value = "SELECT a.id_autore, a.nome, a.cognome FROM Autore a WHERE a.id_autore NOT IN (SELECT a.id_autore FROM Autore a JOIN Scritto s ON a.id_autore = s.id_autore WHERE s.id=?1)", nativeQuery = true)
	public List<Autore> trovaAutoriDiversi(int id);
	
	@Modifying
    @Query(value = "DELETE FROM Scritto WHERE id = ?1 AND id_autore = ?2", nativeQuery = true)
    public void deleteScritto(int id, int id_autore);
	
}
