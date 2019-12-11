package net.assignment.booksLoan.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Utente {

    private int n_tessera;
    private String nome;
    private String cognome;
    private String e_mail;

    public Utente() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getN_tessera() {
        return n_tessera;
    }

    public void setN_tessera(int n_tessera) {
        this.n_tessera = n_tessera;
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

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }
}
