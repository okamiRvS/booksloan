package net.assignment.booksLoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.assignment.booksLoan.model.Autore;
import net.assignment.booksLoan.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

	@Query(value = "SELECT l.id, l.titolo, l.anno FROM Libro l JOIN Sequel s on l.id = s.id_1 where l.id = ?1", nativeQuery = true)
	public List<Libro> trovaSequel(int id);

	@Query(value = "SELECT l.id, l.titolo, l.anno FROM Libro l WHERE l.id NOT IN (SELECT l.id FROM Libro l JOIN Sequel s ON l.id = s.id_1 WHERE s.id_1=?1 AND s.id_2!=?1)", nativeQuery = true)
	public List<Libro> trovaLibriDiversi(int id);

	@Modifying
    @Query(value = "INSERT INTO Sequel (id, id_autore) VALUES (?1, ?2)", nativeQuery = true)
	public void setSequel(int id_libro, int id_libro2);
}
