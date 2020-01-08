package net.assignment.booksLoan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class BooksLoan {

	public static void main(String[] args) {
		SpringApplication.run(BooksLoan.class, args);
	}

}
