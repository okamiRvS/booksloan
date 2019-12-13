package net.assignment.booksLoan.model;

import javax.persistence.Column;

public class Cliente extends Utente{
	
	@Column
	private String nome;
	@Column
    private String cognome;
	@Column
	private String residenza;
	
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

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}
}
