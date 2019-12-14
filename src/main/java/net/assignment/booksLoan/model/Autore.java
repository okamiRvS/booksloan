package net.assignment.booksLoan.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

@Entity
@Table(name="autore", schema="booksloan")
public class Autore {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_autore;
    private String nome;
    private String cognome;

    
    @ManyToMany
    @JoinTable(
    		  name = "scritto", 
    		  joinColumns = @JoinColumn(name = "id_autore"), 
    		  inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<Libro> libri = new HashSet<>();
    
    
    public Autore() {

    }

    public int getId_autore() {
        return id_autore;
    }

    public void setId_autore(int id_autore) {
        this.id_autore = id_autore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

}
