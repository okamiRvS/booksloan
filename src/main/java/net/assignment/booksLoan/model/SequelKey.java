package net.assignment.booksLoan.model;

import java.io.Serializable;

public class SequelKey implements Serializable{
	
	  private int id_1;
	  private int id_2;

	  public SequelKey() {
	  }
	  
	  

	public SequelKey(int id_1, int id_2) {
		super();
		this.id_1 = id_1;
		this.id_2 = id_2;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_1;
		result = prime * result + id_2;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SequelKey other = (SequelKey) obj;
		if (id_1 != other.id_1)
			return false;
		if (id_2 != other.id_2)
			return false;
		return true;
	}



	public int getId_1() {
		return id_1;
	}



	public void setId_1(int id_1) {
		this.id_1 = id_1;
	}



	public int getId_2() {
		return id_2;
	}



	public void setId_2(int id_2) {
		this.id_2 = id_2;
	}

	
	  
}