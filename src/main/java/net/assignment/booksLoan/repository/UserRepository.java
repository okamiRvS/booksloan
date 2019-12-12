package net.assignment.booksLoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.assignment.booksLoan.model.Utente;

public interface UserRepository extends JpaRepository<Utente, Integer> {
}
