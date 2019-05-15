package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dbconfig.HibernateConfig;
import model.Book;
import model.User;

public class UserDAO {
	
	@Autowired
	HibernateConfig hibernateConfig;
	
	public void addUser(User u) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(u);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public List<User> getUsers() {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from User");
		List<User> mLis = query.list();
		session.getTransaction().commit();
		session.close();
		return mLis;
	}
	
	public List<User> searchUserByEmail(String email) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from User where Email like concat('%',:email,'%')");
		query.setParameter("email", email);
		List<User> mLis=query.list();
		session.getTransaction().commit();
		session.close();
		return mLis;
	}
	
	
	public void deleteUser(String email) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("delete from User where Email=:email");
		query.setParameter("email", email);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	public void update(User u) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println(u.getEmail());
		System.out.println(u.getName());
		Query query=session.createQuery("update User set CNP=:cnp,"+"Password=:password,"+"Name=:name,"+"Adress=:adress,"+"Phone=:phone,"+"Type=:type"+" where Email=:email");
		query.setParameter("cnp", u.getCnp());
		query.setParameter("password", u.getPassword());
		query.setParameter("name", u.getName());
		query.setParameter("adress", u.getAdress());
		query.setParameter("phone", u.getPhone());
		query.setParameter("type", u.getType());
		query.setParameter("email", u.getEmail());
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	public User getUserByEmailPassword(User u) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from User where Email=:email and "+"Password=:password");
		query.setParameter("email", u.getEmail());
		query.setParameter("password", u.getPassword());
		List<User> mLis=query.list();
		session.getTransaction().commit();
		session.close();
		if(mLis.isEmpty())
			return null;
		return mLis.get(0);
	}
	
	public User getUserByEmail(String email) {
		Session session = hibernateConfig.buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from User where Email=:email");
		query.setParameter("email", email);
		List<User> mLis=query.list();
		session.getTransaction().commit();
		session.close();
		if(mLis.isEmpty())
			return null;
		return mLis.get(0);
	}

}
