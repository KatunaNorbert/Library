package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="BorrowedBook")
public class BorrowedBook {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="Id")
	private int id;
	
	@Column(name="Date")
	private String date;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="UserEmail")
	private User user;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="ExemplaryId")
	private Exemplary exemplary;
	
	public Exemplary getExemplary() {
		return exemplary;
	}

	public void setExemplary(Exemplary exemplary) {
		this.exemplary = exemplary;
	}

	public BorrowedBook() {
		this.id=0;
		this.date="";
		this.exemplary=new Exemplary();
		this.user=new User();
	}
	
	public BorrowedBook(int id, String uemail, int idBook, String date, Exemplary e, User u) {
		super();
		this.id = id;
		this.date = date;
		this.exemplary=e;
		this.user=u;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
