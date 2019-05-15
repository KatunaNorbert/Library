package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Exemplary")
public class Exemplary {
	
	@Id
	//@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="Id")
	private int id;
	
	@Column(name="State")
	private String state;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="IdBook")
	private Book book;
	
	@JsonBackReference
	@OneToMany(targetEntity=BorrowedBook.class, mappedBy="exemplary", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<BorrowedBook> bBooks;
	
	
	public List<BorrowedBook> getbBooks() {
		return bBooks;
	}

	public void setbBooks(List<BorrowedBook> bBooks) {
		this.bBooks = bBooks;
	}

	public Exemplary() {
		this.id=0;
		this.state="";
		this.book=new Book();
		this.bBooks=new ArrayList<BorrowedBook>();
	}
	
	public void set(Exemplary e) {
		this.id=e.getId();
		this.state=e.getState();
		this.book=e.getBook();
		this.bBooks=e.getbBooks();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book idBook) {
		this.book = idBook;
	}

}
