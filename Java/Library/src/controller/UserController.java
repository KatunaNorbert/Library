package controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BookDAO;
import dao.UserDAO;
import model.Book;
import model.User;

@Path("/user")
public class UserController {
	
	private static User user=new User();
	@Autowired
	UserDAO ud = new UserDAO();
	
	@GET
	@Path("/logout")
	@Produces(MediaType.TEXT_HTML)
	public String logout() {
		this.user=new User();
		return "done";
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_HTML)
	public String getUsers() {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(ud.getUsers());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/getByEmail/{email}")
	@Produces(MediaType.TEXT_HTML)
	public String getUsersByEmail(@PathParam("email") String email) {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(ud.searchUserByEmail(email));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/getSelected")
	@Produces(MediaType.TEXT_HTML)
	public String getSelecte() {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/getType")
	@Produces(MediaType.TEXT_HTML)
	public String getLoggedUsers() {
		return this.user.getType();
	}
	
	@GET
	@Path("/add/{cnp}/{email}/{password}/{name}/{adress}/{phone}/{type}")
	@Produces(MediaType.TEXT_HTML)
	public String addUsers(@PathParam("cnp") int cnp,@PathParam("email") String email,@PathParam("password") String password,@PathParam("name") String name,@PathParam("adress") String adress,@PathParam("phone") int phone,@PathParam("type") String type) {
		User us=new User(cnp,email,password,name,adress,phone,type);
		try{
			this.ud.addUser(us);
		}catch(Error e) {
			return "error";
		}
		return "done";
	}
	
	@GET
	@Path("/update/{cnp}/{email}/{password}/{name}/{adress}/{phone}/{type}")
	@Produces(MediaType.TEXT_HTML)
	public String updateUsers(@PathParam("cnp") int cnp,@PathParam("email") String email,@PathParam("password") String password,@PathParam("name") String name,@PathParam("adress") String adress,@PathParam("phone") int phone,@PathParam("type") String type) {
		User us=new User(cnp,email,password,name,adress,phone,type);
		try{
			this.ud.update(us);;
		}catch(Error e) {
			return "error";
		}
		return "done";
	}
	
	@GET
	@Path("/delete/{email}")
	@Produces(MediaType.TEXT_HTML)
	public String deleteUsers(@PathParam("email") String email) {
		try{
			this.ud.deleteUser(email);
		}catch(Error e) {
			return "error";
		}
		return "done";
	}
	
	@GET
	@Path("/logIn/{email}/{password}")
	@Produces(MediaType.TEXT_HTML)
	public String LogIn(@PathParam("email") String email,@PathParam("password") String password) {
		User u=new User();
		u.setEmail(email);
		u.setPassword(password);
		if(ud.getUserByEmailPassword(u)==null) {
			return "register";
		}
		u.setUser(ud.getUserByEmailPassword(u));
		user.setUser(u);
		if(u.getType().equals("user")) {
			return "user";
		}else {
		return "admin";	
		}
	}
	
	@GET
	@Path("/logStatus")
	@Produces(MediaType.TEXT_HTML)
	public String checkLogStatus() {
		if(this.user.getName()=="") {
			return "false";
		}else {
			return user.getEmail();
		}
		
	}
	
}
