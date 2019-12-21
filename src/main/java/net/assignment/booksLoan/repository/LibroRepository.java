package net.assignment.booksLoan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.assignment.booksLoan.model.Autore;
import net.assignment.booksLoan.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
}
