package net.assignment.booksLoan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="utente", schema="booksloan")
public class Utente {

    @Id
    @Column(name = "n_tessera")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int n_tessera;
    
    @Column
    private String password;
    
    @Column
    private String username;
    
    @Column
    private String e_mail;

    @Column
    private String ruolo;
    
    public Utente() {
    }

    public int getN_tessera() {
        return n_tessera;
    }

    public void setN_tessera(int n_tessera) {
        this.n_tessera = n_tessera;
    }

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

}
