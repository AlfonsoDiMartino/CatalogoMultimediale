package Server.DAO;
import javax.persistence.*;
import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Server.DAO.util.HibernateUtil;

@Entity
@Table(name="Autore")
public class AutoreDAO {

	@Id
	@GeneratedValue
	@Column(name="idAutore")
	private Integer idAutore;
	private String nome;
	private String cognome;

	public Integer getIdAutore() {
		return this.idAutore;
	}

	public void setIdAutore(Integer idAutore) {
		this.idAutore = idAutore;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public boolean save() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			session.save(this);
			session.getTransaction().commit();
		}catch(HibernateException he){
			System.out.println(he.getMessage());
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

	public static AutoreDAO getAutoreById(Integer idAutore) {
		if (idAutore == null)
			return null;
		AutoreDAO autore;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			autore = (AutoreDAO) session.get(AutoreDAO.class, idAutore);
			session.getTransaction().commit();
			session.close();
			return autore;
		}catch(HibernateException he){
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Integer> getListaIdAutori() {
		String hql = 	"SELECT A.idAutore FROM AutoreDAO A ORDER BY A.cognome";
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			return (List<Integer>)query.list();
		} catch(HibernateException he){
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	// restituisce true se l'autore non esiste!
	public static Boolean verificaEsistenzaAutore(String nome, String cognome) {
		if (nome == null || cognome == null)
			return false;
		String hql = 	"SELECT A.idAutore, A.nome, A.cognome FROM AutoreDAO as A WHERE A.nome = '" +
						nome + "'and A.cognome = '" + cognome + "'";
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<AutoreDAO> result = (List<AutoreDAO>)query.list();
			return !result.isEmpty();
		} catch(HibernateException he){
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}

}
