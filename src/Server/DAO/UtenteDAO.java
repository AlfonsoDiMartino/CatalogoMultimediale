package Server.DAO;
import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;
import Server.DAO.util.HibernateUtil;


@Entity
@Table(name="Utente")
public class UtenteDAO {

	@Id
	@GeneratedValue
	@Column(name="idUtente")
	private Integer idUtente;
	private String email;
	private String password;
	private String nome;
	private String cognome;
	
	@OneToOne
	@JoinColumn(name="idCatalogo_fk")
	private CatalogoUtenteDAO catalogo;

	public Integer getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
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

	public List<PreferenzaUtenteDAO> getListPreferenze() {
		return PreferenzaUtenteDAO.getListaPreferenzeUtente(this);
	}
	
	public CatalogoUtenteDAO getCatalogo() {
		return this.catalogo;
	}
	
	public void setCatalogo(CatalogoUtenteDAO catalogo) {
		this.catalogo = catalogo;
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
			session.getTransaction().rollback();
			return false;
		}finally{
			session.close();
		}
		return true;
	}

	public static UtenteDAO getUtenteById(Integer idUtente) {
		if (idUtente == null)
			return null;
		UtenteDAO utente;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			utente = (UtenteDAO) session.get(UtenteDAO.class, idUtente);
			session.getTransaction().commit();
			session.close();
			return utente;
		}catch(HibernateException he){
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}


}
