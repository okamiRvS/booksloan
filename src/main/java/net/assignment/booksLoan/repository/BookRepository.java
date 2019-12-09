package net.assignment.booksLoan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.assignment.booksLoan.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
