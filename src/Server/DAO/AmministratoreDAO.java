package Server.DAO;
import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Server.DAO.util.HibernateUtil;


@Entity
@Table(name="Amministratore")
public class AmministratoreDAO {

	@Id
	@GeneratedValue
	@Column(name="idAmministratore")
	private Integer idAmministratore;
	private String email;
	private String password;

	public Integer getIdAmministratore() {
		return this.idAmministratore;
	}

	public void setIdAmministratore(Integer idAmministratore) {
		this.idAmministratore = idAmministratore;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean save() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			session.save(this);
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			System.out.println(he);
			return false;
		}finally{
			session.close();
		}
		return true;
	}
	
	public boolean update() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			session.update(this);
			session.getTransaction().commit();
		}catch(HibernateException he){
			System.out.println(he.getMessage());
			session.getTransaction().rollback();
			return false;
		}finally{
			session.close();
		}
		return true;
	}


	public static AmministratoreDAO getAmministratoreById(Integer idAmministratore) {
		if (idAmministratore == null)
			return null;
		AmministratoreDAO amministratore;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			amministratore = (AmministratoreDAO) session.get(AmministratoreDAO.class, idAmministratore);
			session.getTransaction().commit();
			session.close();
			return amministratore;
		}catch(HibernateException he){
			System.out.println(he.getMessage());
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

}
