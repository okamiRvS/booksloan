package net.assignment.booksLoan.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="libro", schema="booksloan")
public class Libro {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titolo;
    private String anno;
    
    @ManyToMany
    @JoinTable(
    		  name = "scritto", 
    		  joinColumns = @JoinColumn(name = "id"), 
    		  inverseJoinColumns = @JoinColumn(name = "id_autore"))
    private List<Autore> listAutore;

    public Libro() {
    }

    public int getId() {
        return id;
    }

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public List<Autore> getListAutore() {
		return listAutore;
	}

	public void setListAutore(List<Autore> listAutore) {
		this.listAutore = listAutore;
	}
	
}