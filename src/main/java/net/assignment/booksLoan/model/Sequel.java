package net.assignment.booksLoan.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(SequelKey.class)
public class Sequel {

	@Id
    private int id_1;
	@Id
    private int id_2;

    public Sequel() {

    }

    public int getId_1() {
        return id_1;
    }

    public void setId_1(int id_1) {
        this.id_1 = id_1;
    }

    //https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/composite-primary-key.html
    public int getId_2() {
        return id_2;
    }

    public void setId_2(int id_2) {
        this.id_2 = id_2;
    }
}
