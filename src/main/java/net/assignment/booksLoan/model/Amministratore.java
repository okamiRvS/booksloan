package net.assignment.booksLoan.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ROLE_ADMIN")
public class Amministratore extends Utente{

	@Column
	private String tipologia_contratto;

	public String getTipologia_contratto() {
		return tipologia_contratto;
	}

	public void setTipologia_contratto(String tipologia_contratto) {
		this.tipologia_contratto = tipologia_contratto;
	}

    public Amministratore(String tipologia_contratto) {
        super();
        this.tipologia_contratto = tipologia_contratto;
    }

    public Amministratore() {
        super();
        // TODO Auto-generated constructor stub
    }


}
