package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="User")
@XmlRootElement
public class User{
	
	@Column(name="CNP")
	private int cnp;
	
	@Id
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Adress")
	private String adress;
	
	@Column(name="Phone")
	private int phone;
	
	@Column(name="Type")
	private String type;
	
	@JsonBackReference
	@OneToMany(targetEntity=BorrowedBook.class, mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<BorrowedBook> bBooks;
	
	public List<BorrowedBook> getbBooks() {
		return bBooks;
	}

	public void setbBooks(List<BorrowedBook> bBooks) {
		this.bBooks = bBooks;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User() {
		this.cnp=0;
		this.email="";
		this.name="";
		this.adress="";
		this.password="";
		this.phone=0;
		this.type="";
		this.bBooks=new ArrayList<BorrowedBook>();
	}
	
	public void setUser(User u) {
		this.cnp = u.getCnp();
		this.email = u.getEmail();
		this.password = u.getPassword();
		this.name = u.getName();
		this.adress = u.getAdress();
		this.phone = u.getPhone();
		this.type = u.getType();
	}

	public User(int cnp, String email, String password, String name, String adress, int phone, String type) {
		super();
		this.cnp = cnp;
		this.email = email;
		this.password = password;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.type = type;
	}

	public int getCnp() {
		return cnp;
	}

	public void setCnp(int cnp) {
		this.cnp = cnp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
}
