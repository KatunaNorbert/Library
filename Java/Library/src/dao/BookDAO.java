package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dbconfig.HibernateConfig;
import model.Book;
import model.User;

public class BookDAO {
	
	@Autowired
	HibernateConfig hibernateConfig;
	
	public void addBook(Book b) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(b);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public List<Book> getBookByName(String name) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from Book where Name like concat('%',:name,'%')");
		query.setParameter("name", name);
		List<Book> mLis=query.list();
		session.getTransaction().commit();
		session.close();
		return mLis;
	}
	
	public Book getBookById(int id) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from Book where Id=:id");
		query.setParameter("id", id);
		List<Book> mLis=query.list();
		session.getTransaction().commit();
		session.close();
		return mLis.get(0);
	}
	
	public List<Book> getAvExemp() {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from Book as b inner join b.exemplaries as ex where ex.state=:s");
		List<Book> mLis = new ArrayList<Book>();
		query.setParameter("s", "available");
		List<?> result =query.list();
		Iterator itr = result.iterator();
		while(itr.hasNext()){
		   Object[] books = (Object[]) itr.next();
		   Book b=(Book)books[0];
		   System.out.println(b.getName());
		   mLis.add(b);
		}
		session.getTransaction().commit();
		session.close();
		return mLis;
	}
	
	public List<Book> getBooks() {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from Book");
		List<Book> mLis = query.list();
		session.getTransaction().commit();
		session.close();
		return mLis;
	}
	
	public void removeBook(int id) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("delete from Book where Id=:id");
		q.setParameter("id", id);
		q.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void updateBook(Book b) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("update Book set Name=:name,"+"Author=:author,"+"Type=:type"+" where Id=:id");
		query.setParameter("author", b.getAuthor());
		query.setParameter("type", b.getType());
		query.setParameter("name", b.getName());
		query.setParameter("id", b.getId());
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	public void borrowBook(int id,String email) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("inset into BorrowedBook values(UserEmail=:email,IdBook=:b");
		q.setParameter("b", id);
		q.setParameter("email", email);
		q.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

}
