package net.assignment.booksLoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.assignment.booksLoan.model.Prestito;

@Repository
public interface PrestitoRepository extends JpaRepository<Prestito, Integer> {
	
	@Query (value = "SELECT * FROM Prestito p WHERE p.n_tessera = ?1", nativeQuery = true)
    public List<Prestito> prenotazioni(int n_tessera);
	
	@Modifying
	@Query(value = "DELETE FROM Prestito WHERE isbn = ?1", nativeQuery = true)
	public void deleteByKey(Long isbn);
	
}