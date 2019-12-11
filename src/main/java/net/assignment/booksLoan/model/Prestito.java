package net.assignment.booksLoan.model;

public class Prestito {

    private String data_inizio;
    private String data_consegna;
    private Long isbn;
    private int n_tessera;

    public Prestito() {

    }

    public String getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(String data_inizio) {
        this.data_inizio = data_inizio;
    }

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
