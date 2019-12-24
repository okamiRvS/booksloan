package net.assignment.booksLoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.assignment.booksLoan.model.Sequel;

public interface SequelRepository extends JpaRepository<Sequel, Integer> {

	@Modifying
    @Query(value = "INSERT INTO Sequel VALUES (?1, ?2)", nativeQuery = true)
	public void setSequel(int id_libro, int id_libro2);
}
