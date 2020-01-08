package net.assignment.booksLoan.model;

import javax.persistence.Column;

public class Cliente extends Utente{

    @Column
    private String residenza;

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}
}
