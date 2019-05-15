package controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BookDAO;
import dao.ExemplaryDAO;
import model.Book;
import model.Exemplary;

@Path("/exemplary")
public class ExemplaryController {
	
	@Autowired
	ExemplaryDAO ed=new ExemplaryDAO();
	
	@Autowired
	BookDAO bd=new BookDAO();
	
	static Exemplary e=new Exemplary();
	
	@GET
	@Path("/add/{idBook}")
	@Produces(MediaType.TEXT_HTML)
	public String addExemplary(@PathParam("idBook") int idBook) {
		Book b=new Book();
		b.setBook(bd.getBookById(idBook));
		Exemplary e=new Exemplary();
		e.setBook(b);
		e.setState("available");
		try {
			this.ed.addExemplary(e);;
		} catch (Error err) {
			return "error";
		}
		return "done";
	}
	
	@GET
	@Path("/saveExemplary/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String saveExemplary(@PathParam("id") int id) {
		e.set(ed.getExemplaryById(id));
		if(e.getState()!="") {
			return "done";
		}else {
			return "error";
		}
	}
	
	@GET
	@Path("/getSelectedExemplary")
	@Produces(MediaType.TEXT_HTML)
	public String getSelectedExemplary() {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(e);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/getByName/{name}")
	@Produces(MediaType.TEXT_HTML)
	public String getBook(@PathParam("name") String name) {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(ed.getByName(name));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	
	@GET
	@Path("/getAvailableExemp")
	@Produces(MediaType.TEXT_HTML)
	public String getAvExemp() {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(ed.getAvailable());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String deleteExemplary(@PathParam("id") int id) {
		try {
			this.ed.removeExemplary(id);
		} catch (Error err) {
			return "error";
		}
		return "done";
	}
	
	@GET
	@Path("/update/{id}/{status}")
	@Produces(MediaType.TEXT_HTML)
	public String addBooks(@PathParam("id") int id,@PathParam("status") String status) {
		try {
			this.ed.updateStatus(id,status);;
		} catch (Error err) {
			return "error";
		}
		return "done";
	}

}
