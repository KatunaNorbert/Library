package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="Book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="Id")
	private int id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Author")
	private String author;
	
	@Column(name="Type")
	private String type;
	
	@JsonBackReference
	@OneToMany(targetEntity=Exemplary.class, mappedBy="book", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Exemplary> exemplaries;
	
	public Book() {
		this.id=0;
		this.name ="";
		this.author ="";
		this.type = "";
		this.exemplaries=new ArrayList<Exemplary>();
	}
	
	public List<Exemplary> getExemplaries() {
		return exemplaries;
	}

	public void setExemplaries(List<Exemplary> exemplaries) {
		this.exemplaries = exemplaries;
	}

	public Book(String name, String author, String type) {
		super();
		this.id=0;
		this.name = name;
		this.author = author;
		this.type = type;
	}

	public void setBook(Book b){
		this.id=b.getId();
		this.name=b.getName();
		this.author=b.getAuthor();
		this.type=b.getType();
		this.exemplaries=b.getExemplaries();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
