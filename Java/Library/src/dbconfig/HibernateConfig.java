package dbconfig;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
	
	public static SessionFactory buildSessionFactory() {
		SessionFactory sessionFactoryObj = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		return sessionFactoryObj;
	}

}
