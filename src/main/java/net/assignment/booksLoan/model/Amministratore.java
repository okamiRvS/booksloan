package net.assignment.booksLoan.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Amministratore extends Utente{
	
	@Autowired
	private String tipologia_contratto;

	public String getTipologia_contratto() {
		return tipologia_contratto;
	}

	public void setTipologia_contratto(String tipologia_contratto) {
		this.tipologia_contratto = tipologia_contratto;
	}
	
}
