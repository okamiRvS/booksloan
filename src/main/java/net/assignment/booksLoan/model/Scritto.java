package net.assignment.booksLoan.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Scritto {

    private int id;
    private int id_autore;

    public Scritto() {

    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId_autore() {
        return id_autore;
    }

    public void setId_autore(int id_autore){
        this.id_autore = id_autore;
    }
}