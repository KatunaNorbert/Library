package controller;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BookDAO;
import dao.BorrowBookDAO;
import dao.ExemplaryDAO;
import dao.UserDAO;
import model.Book;
import model.BorrowedBook;
import model.Exemplary;
import model.User;

@Path("/book")
public class BookController {
	
	@Autowired
	BookDAO bd = new BookDAO();
	
	@Autowired
	BorrowBookDAO bb = new BorrowBookDAO();
	
	@Autowired
	ExemplaryDAO ed = new ExemplaryDAO();
	
	@Autowired
	UserDAO ud = new UserDAO();
	
	static Book b=new Book();
	
	
	@GET
	@Path("/getBorrowedBook")
	@Produces(MediaType.TEXT_HTML)
	public String getBorrowedBook() {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(bb.getAll());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/getBorrowedBookByEmail/{email}")
	@Produces(MediaType.TEXT_HTML)
	public String getBorrowedBook(@PathParam("email") String email) {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(bb.getByEmail(email));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_HTML)
	public String getBooks() {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(bd.getBooks());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/add/{name}/{author}/{type}")
	@Produces(MediaType.TEXT_HTML)
	public String addBooks(@PathParam("name") String name,@PathParam("author") String author,@PathParam("type") String type) {
		Book b=new Book(name,author,type);
		try {
			this.bd.addBook(b);
		} catch (Error e) {
			return "error";
		}
		return "done";
	}
	
	@GET
	@Path("/update/{name}/{author}/{type}")
	@Produces(MediaType.TEXT_HTML)
	public String updateBooks(@PathParam("name") String name,@PathParam("author") String author,@PathParam("type") String type) {
		Book bk=new Book(name,author,type);
		bk.setId(this.b.getId());
		try {
			this.bd.updateBook(bk);
		} catch (Error e) {
			return "error";
		}
		return "done";
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String deleteBook(@PathParam("id") int id) {
		try {
			bd.removeBook(id);
		} catch (Error e) {
			return "error";
		}
		return "done";
	}
	
	@GET
	@Path("/getByName/{name}")
	@Produces(MediaType.TEXT_HTML)
	public String getBook(@PathParam("name") String name) {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(bd.getBookByName(name));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/getSelected")
	@Produces(MediaType.TEXT_HTML)
	public String getSelectedBook() {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(b);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	@GET
	@Path("/save/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String saveBook(@PathParam("id") int id) {
		b.setBook(bd.getBookById(id));
		b.setExemplaries(null);
		if(b.getAuthor()!="") {
			return "done";
		}else {
			return "error";
		}
	}
	
	
	@GET
	@Path("/borrow/{id}/{email}/{date}")
	@Produces(MediaType.TEXT_HTML)
	public String borrowBook(@PathParam("id") int id,@PathParam("email") String email,@PathParam("date") String date) {
		Exemplary e=ed.getExemplaryById(id);
		e.setState("borrowed");
		User u=ud.getUserByEmail(email);
		BorrowedBook br= new BorrowedBook();
		br.setExemplary(e);
		br.setUser(u);
		br.setDate(date);
		try{
			bb.borrowBook(br);
		}catch(Error er) {
			return "false";
		}
		ed.updateStatus(e.getId(),"borrowed");
		return "done";
		
	}
	
	@GET
	@Path("/return/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String borrowBook(@PathParam("id") int id) {
		try{
			bb.returnBook(id);;
		}catch(Error e) {
			return "false";
		}
		ed.updateStatus(id, "available");
		return "done";
		
	}

}
