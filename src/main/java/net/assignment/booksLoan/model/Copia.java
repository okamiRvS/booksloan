package net.assignment.booksLoan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="copia", schema="booksloan")
public class Copia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "isbn")
    private Long isbn;
	@Column(name = "disponibilita")
    private boolean disponibilita;

	@OneToOne(mappedBy = "copia")
    private Prestito prestito;

	@ManyToOne
	@JoinColumn(name = "id")
	private Libro libro;
	
    public Copia() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public boolean getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public String toString() {
		return "Copia [isbn=" + isbn + ", disponibilita=" + disponibilita + ", prestito=" + prestito + ", libro="
				+ libro + "]";
	}
    
}
