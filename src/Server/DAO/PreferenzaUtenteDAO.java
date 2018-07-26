package Server.DAO;
import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;
import Server.DAO.util.HibernateUtil;

@Entity
@Table(name="PreferenzaUtente")
public class PreferenzaUtenteDAO {
	
	@Id
	@GeneratedValue
	@Column(name="idPreferenza")
	private Integer idPreferenza;

	@ManyToOne
	@JoinColumn(name="idUtente_fk")
	private UtenteDAO utente;
	@ManyToOne
	@JoinColumn(name="idTipologia_fk")
	private TipologiaDAO tipologia;
	@ManyToOne
	@JoinColumn(name="idAutore_fk")
	private AutoreDAO autore;
	
	public Integer getIdPreferenza() {
		return idPreferenza;
	}
	
	public void setIdPreferenza(Integer idPreferenza) {
		this.idPreferenza = idPreferenza;
	}

	public UtenteDAO getUtente() {
		return this.utente;
	}

	public void setUtente(UtenteDAO utente) {
		this.utente = utente;
	}

	public TipologiaDAO getTipologia() {
		return this.tipologia;
	}

	public void setTipologia(TipologiaDAO tipologia) {
		this.tipologia = tipologia;
	}

	public AutoreDAO getAutore() {
		return this.autore;
	}

	public void setAutore(AutoreDAO autore) {
		this.autore = autore;
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
	
	@SuppressWarnings("unchecked")
	public static List<PreferenzaUtenteDAO> getListaPreferenzeUtente(UtenteDAO utente) {
		String hql = 	"FROM PreferenzaUtenteDAO WHERE idUtente_fk = " + utente.getIdUtente();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		List<PreferenzaUtenteDAO> result = null;
		try {
			Query query = session.createQuery(hql);
			result = (List<PreferenzaUtenteDAO>) query.list();
			return result;
		} catch(HibernateException he){
			System.out.println(he.getMessage());
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

}
