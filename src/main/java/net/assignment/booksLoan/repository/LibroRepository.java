package net.assignment.booksLoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.assignment.booksLoan.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

	@Query(value = "SELECT l2.id, l2.titolo, l2.anno FROM Libro l1 JOIN Sequel s on l1.id = s.id_1 JOIN Libro l2 on l2.id = s.id_2 WHERE l1.id = ?1", nativeQuery = true)
	public List<Libro> trovaSequel(int id);

	@Query(value = "SELECT l.id, l.titolo, l.anno FROM Libro l WHERE l.id NOT IN (SELECT s.id_2 FROM Libro l JOIN Sequel s ON l.id = s.id_1 WHERE s.id_1=?1) AND l.id != ?1", nativeQuery = true)
	public List<Libro> trovaLibriDiversi(int id);

	@Modifying
    @Query(value = "INSERT INTO Sequel VALUES (?1, ?2)", nativeQuery = true)
	public void setSequel(int id_libro, int id_libro2);

	@Modifying
    @Query(value = "DELETE FROM Sequel WHERE id_1 = ?1 OR id_2 = ?1", nativeQuery = true)
    public void deleteSequel(int id);

	@Query(value = "SELECT * FROM Libro WHERE titolo = ?1", nativeQuery = true)
	public Libro ricerca(String titolo);
	
}
