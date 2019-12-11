package net.assignment.booksLoan.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Autore {

    private int id_autore;
    private String nome;
    private String cognome;

    public Autore() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
