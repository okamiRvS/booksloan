package net.assignment.booksLoan.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Prestito {

    private String data_inizio;
    private String data_consegna;
    private Long isbn;
    private int n_tessera;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "n_tessera", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente utente;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "isbn", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Copia copia;

    
    public Prestito() {

    }

    @Id
    public String getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(String data_inizio) {
        this.data_inizio = data_inizio;
    }

    // https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/composite-primary-key.html
    public String getData_consegna() {
        return data_consegna;
    }

    public void setData_consegna(String data_consegna) {
        this.data_consegna = data_consegna;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public int getN_tessera() {
        return n_tessera;
    }

    public void setN_tessera(int n_tessera) {
        this.n_tessera = n_tessera;
    }
}
