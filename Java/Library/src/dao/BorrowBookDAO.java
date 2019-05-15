package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dbconfig.HibernateConfig;
import model.Book;
import model.BorrowedBook;

public class BorrowBookDAO {

	@Autowired
	HibernateConfig hibernateConfig;
	
	public void borrowBook(BorrowedBook b) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(b);
		session.getTransaction().commit();	
		session.close();
	}
	
	public List<BorrowedBook> getAll() {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from BorrowedBook");
		List<BorrowedBook> mLis = query.list();
		session.getTransaction().commit();
		session.close();
		return mLis;
	}
	
	public List<BorrowedBook> getByEmail(String email) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from BorrowedBook where UserEmail like concat('%',:email,'%')");
		query.setParameter("email", email);
		List<BorrowedBook> mLis = query.list();
		session.getTransaction().commit();
		session.close();
		return mLis;
	}
	
	public void returnBook(int id) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("delete from BorrowedBook where Id=:id");
		q.setParameter("id", id);
		q.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
	}
}
