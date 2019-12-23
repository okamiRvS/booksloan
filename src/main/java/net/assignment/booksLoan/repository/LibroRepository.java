package net.assignment.booksLoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.assignment.booksLoan.model.Autore;
import net.assignment.booksLoan.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

	@Query(value = "SELECT l.id, l.titolo, l.anno FROM Libro l JOIN Sequel s on l.id = s.id_1 where l.id = ?1", nativeQuery = true)
	public List<Libro> trovaSequel(int id);
}
