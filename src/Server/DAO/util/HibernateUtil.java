package Server.DAO.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	private static SessionFactory createSessionFactory() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	    return sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = createSessionFactory();
		return sessionFactory;
	}
}
