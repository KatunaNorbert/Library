package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dbconfig.HibernateConfig;
import model.Book;
import model.BorrowedBook;
import model.Exemplary;

public class ExemplaryDAO {
	
	@Autowired
	HibernateConfig hibernateConfig;
	
	public List<Exemplary> getAvailable() {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from Exemplary where State=:state");
		query.setParameter("state", "available");
		List<Exemplary> mLis = query.list();
		session.getTransaction().commit();
		session.close();
		return mLis;
	}
	
	public List<Exemplary> getByName(String name) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from Exemplary as e inner join e.book as b where b.name=:name");
		query.setParameter("name", name);
		List<Exemplary> mLis = query.list();
		session.getTransaction().commit();
		session.close();
		return mLis;
	}
	
	public void addExemplary(Exemplary e) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
	}
	
	public void removeExemplary(int id) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("delete from Exemplary where Id=:id");
		q.setParameter("id", id);
		q.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	public void updateStatus(int id,String status) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("update Exemplary set State=:status where Id=:id");
		query.setParameter("status",status);
		query.setParameter("id",id);
		query.executeUpdate();
		session.getTransaction().commit();	
		session.close();
	}
	
	public Exemplary getExemplaryById(int id) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from Exemplary where Id=:id");
		query.setParameter("id", id);
		List<Exemplary> mLis=query.list();
		session.getTransaction().commit();
		session.close();
		return mLis.get(0);
	}

}
