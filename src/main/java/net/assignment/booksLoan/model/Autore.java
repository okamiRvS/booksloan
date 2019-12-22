package net.assignment.booksLoan.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="autore", schema="booksloan")
public class Autore {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_autore;
    @Column
    private String nome;
    @Column
    private String cognome;

    @ManyToMany
    private List<Libro> listAutore;

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
