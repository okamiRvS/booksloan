package net.assignment.booksLoan.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="libro", schema="booksloan")
public class Libro {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	@Column(name = "titolo")
    private String titolo;
	@Column(name = "anno")
    private String anno;
    
    @ManyToMany
    @JoinTable(
			name = "scritto", 
			joinColumns = @JoinColumn(name = "id"), 
			inverseJoinColumns = @JoinColumn(name = "id_autore"))
    private List<Autore> listAutore;
    
    /*
    @ManyToMany(mappedBy="libriSuccessivi")
	private List<Libro> libriPrecedenti;
    */
	
    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(
			name="sequel",
			joinColumns={@JoinColumn(name="id_1")},
			inverseJoinColumns={@JoinColumn(name="id_2")})
	private List<Libro> libriSuccessivi;

	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Copia> listaCopie;
	
    public Libro() {
    }

    public int getId() {
        return id;
    }

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public List<Autore> getListAutore() {
		return listAutore;
	}

	public void setListAutore(List<Autore> listAutore) {
		this.listAutore = listAutore;
	}

	public List<Libro> getLibriSuccessivi() {
		return libriSuccessivi;
	}

	public void setLibriSuccessivi(List<Libro> libriSuccessivi) {
		this.libriSuccessivi = libriSuccessivi;
	}

	public List<Copia> getListaCopie() {
		return listaCopie;
	}

	public void setListaCopie(List<Copia> listaCopie) {
		this.listaCopie = listaCopie;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titolo=" + titolo + ", anno=" + anno + ", listAutore=" + listAutore
				+ ", libriSuccessivi=" + libriSuccessivi + ", listaCopie=" + listaCopie + "]";
	}
	
}