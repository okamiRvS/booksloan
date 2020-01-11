package net.assignment.booksLoan.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ROLE_USER")
public class Cliente extends Utente{

    @Column(name = "residenza")
    private String residenza;

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	public Cliente(String residenza) {
        super();
        this.residenza = residenza;
    }

    public Cliente() {
        super();
    }


}
