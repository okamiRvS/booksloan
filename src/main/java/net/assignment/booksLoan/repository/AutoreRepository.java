package net.assignment.booksLoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.assignment.booksLoan.model.Autore;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, Integer> {
	@Query(value = "SELECT a.id_autore, a.nome, a.cognome FROM Autore a JOIN Scritto s on a.id_autore = s.id_autore where s.id = ?1", nativeQuery = true)
	public List<Autore> trovaAutoreScritto(int id);	
}
